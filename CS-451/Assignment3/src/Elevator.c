#include "Elevator.h"
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>


Elevator* create_elevator() {
    // Allocate memory for the searcher
    Elevator* new_elevator = (Elevator*)malloc(sizeof(Elevator));

    // Handle failed allocation error
    if (new_elevator == NULL) {
        fprintf(stderr, "Memory allocation failed while creating elevator.\n");
        exit(1);
    }

    // Initialize the elevator
    new_elevator->currentFloor = 0;
    new_elevator->currentDir = 1;

    return new_elevator;
}

int dropRequest(int floor) {
    // Return true if this floor was requested, then remove the request
    sem_wait(&requestListSem);
    if(requestList[floor] == 1) {
        requestList[floor] = 0;
        sem_post(&requestListSem);
        return 1;
    }
    else {
        sem_post(&requestListSem);
        return 0;
    }
}

int noRequests() {
    // Return false if there are any requests in the list
    sem_wait(&requestListSem);
    for(int i = 0; i < numFloors; i++) {
        if(requestList[i] != 0) {
            sem_post(&requestListSem);
            return 0;
        }
    }

    sem_post(&requestListSem);
    return 1;
}

void *elevator_thread(void *arg) {
    Elevator* elevator = arg;
    
    while(1) {
        // Update the direction of the elevator
        if(elevator->currentFloor == 0)
            elevator->currentDir = 1;
        else if(elevator->currentFloor == numFloors - 1)
            elevator->currentDir = -1;
        
        // If there are no requests, wait for more
        if(noRequests()) {
            fprintf(stdout, "\tELEVATOR: Waiting for requests...\n");

            for(int i = 0; i <= maxWanderTime; i++) {
                sleep(1);
                if(!noRequests())
                    break;
            }

            // If there are still no requests, end the thread
            if(noRequests()) {
                fprintf(stdout, "\tELEVATOR: The elevator has no more requests. Closing thread.\n");
                pthread_exit(NULL);
            }
        }
        else { // If there are requests, move the elevator
            sleep(1);
            sem_wait(&elevatorFloorSem);
            fprintf(stdout, "\tELEVATOR: Moving from floor %d to floor %d.\n", 
                elevator->currentFloor, elevator->currentFloor + elevator->currentDir);
            elevator->currentFloor += elevator->currentDir;
            sem_post(&elevatorFloorSem);
        }
        
        // Wait for people to board/depart if this floor was requested
        if(dropRequest(elevator->currentFloor)) {
            fprintf(stdout, "\tELEVATOR: Waiting for passengers to load/depart...\n");
            sleep(1);
        }
    }
}

void start_elevator(Elevator* elevator) {
    pthread_create(&(elevator->thread), NULL, elevator_thread, elevator);
}


void destroy_elevator(Elevator* elevator) {
    free(elevator);
}