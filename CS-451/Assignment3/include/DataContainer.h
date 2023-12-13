#ifndef DATACONTAINER_H
#define DATACONTAINER_H

#include <semaphore.h>

// A semaphore to control access to the floor the elevator is on
extern sem_t elevatorFloorSem;
// A semaphore to control access to the request list
extern sem_t requestListSem;

// Request list is an array of size numFloors, where each index
// stores 1 or 0 to indicate if that floor has a pending request
extern int* requestList;

// The number of people in the system
extern int numPeople;
// The max time a person can wander on a floor
extern int maxWanderTime;
// The number of floors in the building
extern int numFloors;

/*
Function Name: init_all_sems
Input to the method: N/A
Output(Return value): N/A
Description: 
    This function initializes all of the semaphores */
extern void init_all_sems();

/*
Function Name: init_all_sems
Input to the method: N/A
Output(Return value): N/A
Description: 
    This function destroys all of the semaphores */
extern void destroy_all_sems();

#endif
