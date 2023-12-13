#include "DataContainer.h"

#ifndef DATACONTAINER_C
#define DATACONTAINER_C

#include <stdio.h>
#include <stdlib.h>

sem_t elevatorFloorSem;
sem_t requestListSem;

int* requestList;
int numPeople = 1;
int maxWanderTime = 10;
int numFloors = 10;

void init_all_sems() {
    if(sem_init(&elevatorFloorSem, 1, 1) == -1) {
        fprintf(stderr, "ERROR: Failed to initialize elevatorFloorSem.\n");
        exit(1);
    }

    if(sem_init(&requestListSem, 1, 1) == -1) {
        fprintf(stderr, "ERROR: Failed to initialize requestListSem.\n");
        exit(1);
    }
}

void destroy_all_sems() {
    sem_destroy(&elevatorFloorSem);
    sem_destroy(&requestListSem);
}

#endif
