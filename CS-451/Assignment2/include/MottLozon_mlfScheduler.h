#ifndef MLFSCHEDULER_H
#define MLFSCHEDULER_H


/** Authors: Samantha Mott and Dylan Lozon
 *  This file exists primarily so the functions
 *  can be defined without cluttering the c file.
*/

#include <unistd.h>
#define MAX_PROCESS 100


// This struct holds the properties needed to define a process
struct Process {
    int pid; // The pid collected from input
    pid_t real_pid; // The real pid running on the machine
    int burst_time; // The time remaining for this process
    int created; // Whether a fork has been performed for this process
};

/*
Function Name: readProcessesFromFile
Input to the method: The name of the file to read, the address of an array of Processes
Output(Return value): The number of Processes read
Description: This reads the input file and creates processes from its contents
*/
int readProcessesFromFile(char* filename, struct Process** processes);

/*
Function Name: execute
Input to the method: A process to execute, and the time slice provided to it
Output(Return value): The amount of time the process was scheduled for
Description: This schedules a process either by forking it, or sending it SIGCONT
*/
int execute(struct Process* process, int time_slice);

/*
Function Name: timerHandler
Input to the method: the number of the signal being recieved
Output(Return value): N/A
Description: This only exists to make sure SIGALRM does not go uncaught
*/
void timerHandler(int signum);

#endif