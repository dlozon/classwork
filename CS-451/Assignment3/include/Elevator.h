#ifndef ELEVATOR_H
#define ELEVATOR_H

#include "DataContainer.h"
#include <pthread.h>

// Define Elevator structure
typedef struct {
    int currentFloor;
    int currentDir;
    pthread_t thread;
} Elevator;

/*
Function Name: create_elevator
Input to the method: N/A
Output(Return value): A new elevator object
Description: 
    This function creates a new elevator */
Elevator* create_elevator();

/*
Function Name: dropRequest
Input to the method: The floor number to drop
Output(Return value): True/1 if the floor was requested
Description: 
    This function checks if a given floor was requested 
    and then removes the request */
int dropRequest(int floorNumber);

/*
Function Name: noRequests
Input to the method: N/A
Output(Return value): True/1 if requestList is only 0's
Description: 
    This function evaluates whether there are any active requests */
int noRequests();

/*
Function Name: elevator_thread
Input to the method: The elevator controlled by this thread
Output(Return value): N/A
Description: 
    This function controls the elevator */
void *elevator_thread(void *arg);

/*
Function Name: start_elevator
Input to the method: The elevator to start
Output(Return value): N/A
Description: 
    This function starts the thread that controls the elevator */
void start_elevator(Elevator* elevator);

/*
Function Name: destroy_elevator
Input to the method: The elevator to destroy
Output(Return value): N/A
Description: 
    This function frees memory held by an elevator */
void destroy_elevator(Elevator* elevator);

#endif