/*
Author: Samantha Mott and Dylan Lozon
Assignment Number: 1
Date of Submission: 10/18/2023
Name of this file: mypseLf.c
Short description of contents: Recreation of ps -eLf
*/

#include <dirent.h>
#include "MottLozon_Helpers.h"

/*
Function Name: Main
Input to the method: n/a
Output(Return value): Information about each process currently running
Brief description of the task: Main function; reads directories and prints information
*/
int main(int argc, char* argv[]) {
    // Get all PIDs
    struct dirent* entry;
    DIR* dir = opendir("/proc");
    char* pids[10000];
    int pidCount = 0;

    if (dir == NULL) {
        fprintf(stderr, "Error opening /proc");
        exit(1);
    }

    while ((entry = readdir(dir)) != NULL) {
        if (entry->d_type == DT_DIR) {
            if (isInteger(entry->d_name)) {
                pids[pidCount] = strdup(entry->d_name);
                pidCount++;
            }
        }
    }
    closedir(dir);

    // Print headers
    fprintf(stdout, "UID\tPID\tPPID\tLWP\tNLWP\tSTIME\tTIME\tCMD\n");


    // Retrieve info for each process
    for (int currPID = 0; currPID < pidCount; currPID++) {
        // Save the current process' ID
        int pid = atoi(pids[currPID]);

        // Assemble the path to /proc/pid/task
        char path[256];
        snprintf(path, sizeof(path), "/proc/%d/task", pid);

        // Get the LWPs for this process
        struct dirent* entry;
        DIR* dir = opendir(path);
        char* lwps[10000];
        int lwpCount = 0;

        if (dir == NULL) {
            fprintf(stderr, "Error opening /proc/%d/task", pid);
            exit(1);
        }

        while ((entry = readdir(dir)) != NULL) {
            if (entry->d_type == DT_DIR) {
                if (isInteger(entry->d_name)) {
                    lwps[lwpCount] = strdup(entry->d_name);
                    lwpCount++;
                }
            }
        }

        // Loop through each LWP that belongs to this process
        for (int currLWP = 0; currLWP < lwpCount; currLWP++) {
            // Get all of the necessary info
            int lwp = atoi(lwps[currLWP]);
            char* uname = getUsernameUID(getProcessUID(pid, lwp));
            int ppid = getPPID(pid, lwp);
            char* start_time = formatProcessStartTime(convertClockTime(getProcessStartTime(pid, lwp)));
            char* scheduled_time = formatTime(convertClockTime(getScheduledTime(pid, lwp)));
            char* command = getProcessCommand(pid, lwp);

            // Print the process info
            // UID
            fprintf(stdout, "%s\t", uname);

            // PID
            fprintf(stdout, "%d\t", pid);

            // PPID
            fprintf(stdout, "%d\t", ppid);

            // LWP
            fprintf(stdout, "%d\t", lwp);

            // NLWP
            fprintf(stdout, "%d\t", lwpCount);

            // STIME
            fprintf(stdout, "%s\t", start_time);
            free(start_time); // Free allocated memory

            // TIME
            fprintf(stdout, "%s\t", scheduled_time);
            free(scheduled_time); // Free allocated memory

            // CMD
            fprintf(stdout, "%s\t", command);
            free(command);  // Free allocated memory

            // Print a newline before moving on to next process
            fprintf(stdout, "\n");
        }
    }
}

