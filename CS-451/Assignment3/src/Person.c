#include "Person.h"
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

Person* create_person(int destinations, int id) {
    Person *new_person = (Person *)malloc(sizeof(Person));

    new_person->id = id;
    new_person->currentFloor = 0;
    new_person->destinationCount = 0;

    if (new_person != NULL) {
        // Allocate memory for floors and wanderTimes based on the number of destinations
        new_person->floors = (int *)malloc(destinations * sizeof(int));
        new_person->wanderTimes = (int *)malloc(destinations * sizeof(int));

        if (new_person->floors == NULL || new_person->wanderTimes == NULL) {
            // Memory allocation failed
            free(new_person->floors);
            free(new_person->wanderTimes);
            free(new_person);
            fprintf(stderr, "Memory allocation failed while creating person %d.\n", id);
            exit(1);
        }
    }
    else {
        fprintf(stderr, "Memory allocation failed while creating person %d.\n", id);
        exit(1);
    }

    return new_person;
}

int pop(int* array, int size) {
    // Handle error if given an empty array
    if (size <= 0) {
        fprintf(stderr, "ERROR: Attempted to pop an element from an empty array.\n");
        exit(1);
    }

    // Store the first element
    int top = array[0];

    // Shift the remaining elements to remove the first element
    for (int i = 0; i < size - 1; ++i) {
        array[i] = array[i + 1];
    }

    return top;
}


void make_request(Person* person, int floor) {
    sem_wait(&requestListSem);
    requestList[floor] = 1;
    sem_post(&requestListSem);
}

void *person_thread(void* args) {
    // Parse the args passed
    person_thread_args* recieved_args = args;
    Person* person = recieved_args->person;
    Elevator* elevator = recieved_args->elevator;
    
    int requestedFloor = 0;
    int elevatorFloor = -1;
    while(1) {
        // Request the elevator to pick them up
        requestedFloor = person->currentFloor;
        // Only make the request if the elevator is not here
        sem_wait(&elevatorFloorSem);
        elevatorFloor = elevator->currentFloor;
        if(requestedFloor != elevatorFloor) {
            make_request(person, requestedFloor);
            fprintf(stdout, "Person %d: Waiting for the elevator to pick them up on floor %d.\n", 
                person->id, requestedFloor);
        }
        sem_post(&elevatorFloorSem);

        // Wait for the elevator to pick them up
        while(elevatorFloor != requestedFloor) {
            sem_wait(&elevatorFloorSem);
            elevatorFloor = elevator->currentFloor;
            sem_post(&elevatorFloorSem);

            sleep(.25);
        }

        // Board the elevator and request a destination
        requestedFloor = pop(person->floors, person->destinationCount);
        // Only make the request if the elevator is not here
        sem_wait(&elevatorFloorSem);
        elevatorFloor = elevator->currentFloor;
        if(requestedFloor != elevatorFloor) {
            make_request(person, requestedFloor);
            fprintf(stdout, "Person %d: Boarded the elevator and will depart on floor %d.\n", 
                person->id, requestedFloor);
        }
        sem_post(&elevatorFloorSem);
        
        // Wait for the elevator to drop them off
        while(elevatorFloor != requestedFloor) {
            sem_wait(&elevatorFloorSem);
            elevatorFloor = elevator->currentFloor;
            sem_post(&elevatorFloorSem);
            
            sleep(.25);
        }

        // Get off of the elevator and wander
        sem_wait(&elevatorFloorSem);
        person->currentFloor = elevator->currentFloor;
        sem_post(&elevatorFloorSem);
        int wanderTime = pop(person->wanderTimes, person->destinationCount);
        fprintf(stdout, "Person %d: Wandering on floor %d for %d seconds.\n", 
            person->id, person->currentFloor, wanderTime);
        sleep(wanderTime);

        // If there are no more destinations, exit the thread
        if(--person->destinationCount <= 0) {
            fprintf(stdout, "Person %d: Completed journey for the day. Closing thread.\n", person->id);
            pthread_exit(NULL);        
        }
    }
}

void start_person(Person* person, Elevator* elevator) {
    person_thread_args* args = (person_thread_args*)malloc(sizeof(person_thread_args));
    if (args == NULL) {
        fprintf(stderr, "Memory allocation failed while creating thread args for Person %d.\n", person->id);
        exit(1);
    }
    args->person = person;
    args->elevator = elevator;

    pthread_create(&(person->thread), NULL, person_thread, args);
}

void destroy_person(Person* person) {
    free(person->floors);
    free(person->wanderTimes);
    free(person);
}