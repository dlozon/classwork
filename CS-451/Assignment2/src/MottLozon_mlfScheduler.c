#include "MottLozon_mlfScheduler.h"
#include "MottLozon_PrimeSearch.h"
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/time.h>
#include <sys/wait.h>


void timerHandler(int signum) { /* Do Nothing */ }


int readProcessesFromFile(char* filename, struct Process** processes) {
    // Open the file for reading
    FILE* file = fopen(filename, "r");
    if (file == NULL) {
        fprintf(stderr, "Failed to open %s", filename);
        return -1;
    }

    // Read and discard the first line of the file
    char buffer[256];
    if (fgets(buffer, sizeof(buffer), file) == NULL) {
        fprintf(stderr, "Failed to read the first line from %s", filename);
        fclose(file);
        return -1;
    }

    // Allocate memory for as many processes as there can be
    struct Process* processArray = (struct Process*)malloc(MAX_PROCESS * sizeof(struct Process));
    if (processArray == NULL) {
        fprintf(stderr, "Failed to allocate memory for process array");
        fclose(file);
        return -1;
    }

    // Create processes using the information in the input file
    int i = 0; // Use i to stop reading after we reach max processes
    while (i < MAX_PROCESS && fscanf(file, "%d %d", &processArray[i].pid, &processArray[i].burst_time) == 2) {
        processArray[i].created = 0;
        i++;
    }

    // Update the pointer given to us so it points at the allocated memory
    *processes = processArray;

    // Close the file and return the number of processes
    fclose(file);
    return i; 
}


int execute(struct Process* process, int time_slice) {
    int scheduled_time;

    // If burst time is one time slice or less, schedule the proc for its whole burst
    if (process->burst_time <= time_slice) {
        scheduled_time = process->burst_time;
        process->burst_time = 0;
    }
    else { // Schedule the proc for one time slice
        scheduled_time = time_slice;
        process->burst_time -= time_slice;
    }

    fprintf(stdout, "Scheduling process %d for %d seconds\n", process->pid, scheduled_time);

    // Fork a new process if it has not been done already
    if (!process->created) {
        pid_t pid = fork();

        if (pid == -1) { // An error occurred
            fprintf(stderr, "Failed to fork process.\n");
            return 1;
        }
        else if (pid == 0) { // This is the child process
            // Start a prime search process and kill child
            execl("./MottLozon_PrimeSearch.exe", "PrimeSearch", (char *)NULL); 
            exit(0);
        }

        // This is the parent
        process->real_pid = pid;
        process->created = 1;
    }
    else { // If proc already exists, just resume it
        kill(process->real_pid, SIGCONT);
    }

    return scheduled_time;
}


int main(int argc, char* argv[]) {
    // Parse the cmd args
    if (argc != 3) {
        fprintf(stderr, "Usage: %s <filename> <time_slice>\n", argv[0]);
        return 1;
    }
    char* filename = argv[1];
    int time_slice = atoi(argv[2]);

    // Set up a timer and register the handler
    struct itimerval timer;
    timer.it_interval.tv_sec = 0;
    timer.it_interval.tv_usec = 0;
    timer.it_value.tv_usec = 0;
    signal(SIGALRM, timerHandler);

    // Create an array of processes and two queues
    struct Process* processes;
    struct Process* first_queue[MAX_PROCESS];
    struct Process* second_queue[MAX_PROCESS];
    int first_queue_size = 0;
    int second_queue_size = 0;

    // Read the input file
    int numProcesses = readProcessesFromFile(filename, &processes);

    // Add all processes to the first queue
    if (numProcesses > 0) {
        for (int i = 0; i < numProcesses; i++) {
            first_queue[i] = &processes[i];
            first_queue_size++;
        }
    }

    // Run the scheduler while there are unfinished processes
    while (first_queue_size > 0 || second_queue_size > 0) {
        struct Process* current_process;
        pid_t current_pid;

        if (first_queue_size > 0) { // There are still processes in the first queue
            // Run the process at the front of the queue
            current_process = first_queue[0];
            timer.it_value.tv_sec = execute(current_process, time_slice);
            setitimer(ITIMER_REAL, &timer, NULL);

            if (current_process->burst_time == 0) {
                // Process finished in the first queue; remove it
                for (int i = 0; i < first_queue_size - 1; i++) {
                    first_queue[i] = first_queue[i + 1];
                }
                first_queue_size--;
            }
            else {
                // Process is not done, move it to the second queue
                second_queue[second_queue_size++] = current_process;
                
                for (int i = 0; i < first_queue_size - 1; i++) {
                    first_queue[i] = first_queue[i + 1];
                }
                first_queue_size--;
            }
        }
        else { // There are still processes in the second queue
            // Run the process at the front of the queue
            current_process = second_queue[0];
            timer.it_value.tv_sec = execute(current_process, time_slice);
            setitimer(ITIMER_REAL, &timer, NULL);

            if (current_process->burst_time <= 0) {
                // Process finished in the second queue; remove it
                for (int i = 0; i < second_queue_size - 1; i++) {
                    second_queue[i] = second_queue[i + 1];
                }
                second_queue_size--;
            }
        }

        // Wait for the current process to finish its burst
        pause(); 

        // Terminate the process if it is done, pause it otherwise
        if(current_process->burst_time == 0)
            kill(current_process->real_pid, SIGTERM);
        else
            kill(current_process->real_pid, SIGTSTP);
    }
    
    // Free the memory and end
    free(processes);
    return 0;
}