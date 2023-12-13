#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Person.h"
#include "Elevator.h"
#include "DataContainer.h"

#define MAX_PEOPLE 100

// Declare a list of all the people
Person *people[MAX_PEOPLE];
// Declare an instance of the Elevator structure
Elevator elevator;

/*
Function Name: readItinerary
Input to the method: The filestream to read input from
Output(Return value): N/A
Description: 
    This fills array 'people' with Person objects using program input from a filestream */
void readItinerary(FILE* input) {
    /**
     * NOTES:
     * We use while loops instead of for, because we do not know how many people we will need to read.
     * The code will read people until it either runs out of people, or until it has counted up to numPeople.
     * If there are less people than numPeople, numPeople will be reduced accordingly.
     * 
     * The code works by reading line by line from stdin. For each line, it will use the first number as 
     * the expected number of pairs, and then attempt to read that many floor-time pairs. 
     * 
     * numPeople is used by this function to limit how many people it can read and index
     * numPeople is also updated at the end of this function
     * maxWanderTime is enforced
     * numFloors is enforced
     * *people is used to store the output of this function
     * 
     * DISCLAIMER: The input is assumed to be correctly formatted, so there is currently little error handling.
    **/

    int remaining = numPeople;
    char currentLine[256];
    fprintf(stdout, "Today's Itinerary:\n");
    // Read people until there are no more
    while (remaining > 0 && fgets(currentLine, sizeof(currentLine), input) != 0) {
        int index = numPeople - remaining;
        int pairs;

        // Attempt to read the number of destinations for this person
        if (sscanf(currentLine, "%d", &pairs) != 1 || pairs <= 0) {
            fprintf(stderr, "ERROR: Invalid pair count in input.\n");
            exit(1);
        }

        // Initialize a person
        people[index] = create_person(pairs, index);

        char *token = strtok(currentLine, " ");
        token = strtok(NULL, " "); 

        // Read this person's schedule for the day
        for (int i = 0; i < pairs; i++) {
            // Check if we reached the end of the line early
            if (token == NULL) {
                fprintf(stderr, "ERROR: Input has incorrectly declared pair count.\n");
                exit(1);
            }

            // Save the second value in the pair (destination floor)
            people[index]->floors[i] = // Ternary expression ensures nonexistent floors are not requested
                atoi(token) > numFloors-1 ? numFloors-1 : 
                atoi(token) < 0 ? 0 : atoi(token);
            token = strtok(NULL, " ");

            // Check if we reached the end of the line while reading a pair
            if (token == NULL) {
                fprintf(stderr, "ERROR: Input is incorrectly formatted.\n");
                exit(1);
            }

            // Save the second value in the pair (time to wander)
            people[index]->wanderTimes[i] = // Ternary expression enforces maxWanderTime
                atoi(token) <= maxWanderTime ? atoi(token) : maxWanderTime;
            token = strtok(NULL, " ");

            // Increase the number of destinations once this one has been successfully read
            people[index]->destinationCount++;

            // Each time we add an entry to the itinerary, print it to the console
            printf("Person %d will spend %d seconds on floor %d. Total destinations for this person: %d\n",
                    people[index]->id, people[index]->wanderTimes[i], people[index]->floors[i], people[index]->destinationCount);
        }
        remaining--;
    }
    // Update the number of people in the system and print it
    numPeople -= remaining;
    printf("Finished reading the itinerary. In total, %d people were accounted for.\n\n", numPeople);
}


int main(int argc, char *argv[]) {
    // Parse command line arguments
    for (int i = 1; i < argc; i += 2) {
        if (strcmp(argv[i], "-p") == 0)
            numPeople = atoi(argv[i + 1]);
        else if (strcmp(argv[i], "-w") == 0)
            maxWanderTime = atoi(argv[i + 1]);
        else if (strcmp(argv[i], "-f") == 0)
            numFloors = atoi(argv[i + 1]);
    }
    // Set numPeople in bounds [0-MAX]
    numPeople = 
        numPeople > MAX_PEOPLE ? MAX_PEOPLE :
        numPeople < 0 ? 0 : numPeople;
    // Ensure that maxWanderTime is at least 1
    maxWanderTime = maxWanderTime < 1 ? 1 : maxWanderTime;
    // Ensure that numFloors is at least 2
    numFloors = numFloors < 2 ? 2 : numFloors;

    fprintf(stdout, "Number of people: %d\n", numPeople);
    fprintf(stdout, "Maximum allowed wandering time: %d\n", maxWanderTime);
    fprintf(stdout, "Number of floors: %d\n\n", numFloors);

    // Initialize the list of requests to be empty
    requestList = (int*)malloc(sizeof(int) * numFloors);
    for(int i = 0; i < numFloors; i++) {
        requestList[i] = 0;
    }

    // Initialize semaphores, elevator and people
    init_all_sems();
    Elevator* elevator = create_elevator();
    readItinerary(stdin);

    // After initalization is complete, create threads and signal the program to start
    start_elevator(elevator);
    for(int i = 0; i < numPeople; i++)
        start_person(people[i], elevator);

    // Wait for all threads to finish
    for(int i = 0; i < numPeople; i++)
        pthread_join(people[i]->thread, NULL);
    pthread_join(elevator->thread, NULL);

    // Clean up the program before exiting
    destroy_all_sems();
    destroy_elevator(elevator);
    for(int i = 0; i < numPeople; i++)
        destroy_person(people[i]);
    free(requestList);

    return 0;
}