#ifndef PERSON_H
#define PERSON_H

#include "DataContainer.h"
#include "Elevator.h"
#include <pthread.h>

// The maximum number of places a person can go to
#define MAX_DESTINATIONS 100

// Define person structure
typedef struct {
    int id;
    int currentFloor;
    int destinationCount;
    int *floors;
    int *wanderTimes;
    pthread_t thread;
} Person;

// Define a struct to pass args to the person thread
typedef struct {
    Person* person;
    Elevator* elevator;
} person_thread_args;

/*
Function Name: create_person
Input to the method: The number of destinations this person will visit
                     This person's id
Output(Return value): A new Person object
Description: 
    This function creates a new person */
Person* create_person(int destinationCount, int id);

/*
Function Name: make_request
Input to the method: The person making the request
                     The floor being requested
Output(Return value): N/A
Description: 
    This function places a request in the requestList */
void make_request(Person* person, int floor);

/*
Function Name: person_thread
Input to the method: a person_thread_args struct
Output(Return value): N/A
Description: 
    This function controls a person */
void *person_thread(void* arg);

/*
Function Name: start_person
Input to the method: The person being started
                     The elevator the person will ride
Output(Return value): N/A
Description: 
    This function starts the thread that controls a person  */
void start_person(Person* person, Elevator* elevator);

/*
Function Name: destroy_person
Input to the method: The person being destroyed
Output(Return value): N/A
Description: 
    This function frees the memory held by a person */
void destroy_person(Person* person);

#endif