#include "MottLozon_PrimeSearch.h"
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    // Create a new process
    pid_t pid = fork();

    if (pid == -1) {
        fprintf(stderr, "Failed to fork process.\n");
        return 1;
    }

    if (pid == 0) { // This is the child process
        // Start a prime search process and kill child
        execl("./PrimeSearch.exe", "PrimeSearch", (char *)NULL); 
        exit(0);
    } 
    else { // This is the parent process

        // Let the process run
        sleep(5);

        // Send a SIGTSTP signal to suspend the PrimeSearch process
        if (kill(pid, SIGTSTP) == -1) {
            fprintf(stderr, "There was an issue suspending the process.\n");
            return 1;
        }

        sleep(5);

        // Send a SIGCONT signal to resume the PrimeSearch process
        if (kill(pid, SIGCONT) == -1) {
            fprintf(stderr, "There was an issue resuming the process.\n");
            return 1;
        }

        sleep(5);

        // Send a SIGTERM signal to terminate the PrimeSearch process
        if (kill(pid, SIGTERM) == -1) {
            fprintf(stderr, "There was an issue terminating the process.");
            return 1;
        }

        // Wait for the child process to terminate
        int status;
        waitpid(pid, &status, 0);
        fprintf(stdout, "Process complete\n");
    }

    return 0;
}
