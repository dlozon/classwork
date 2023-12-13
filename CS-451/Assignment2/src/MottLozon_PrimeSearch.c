#include "MottLozon_PrimeSearch.h"
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <time.h>

// Provides global access to self
struct PrimeSearch* self;

// Global variables used by signal handlers to control process execution
volatile sig_atomic_t searching = 1;
volatile sig_atomic_t terminated = 0;

void signalHandler(int signum) {
    if (signum == SIGTSTP) { // SUSPEND
        searching = 0;

        fprintf(stdout, "PID %d is being suspended, %lu is the largest prime it has found so far.\n", getpid(), getHighestPrime(self));
    } 
    else if (signum == SIGCONT) { // RESUME
        searching = 1;
    } 
    else if (signum == SIGTERM) { // TERMINATE
        searching = 0;
        terminated = 1;

        fprintf(stdout, 
            "PID %d is being terminated. %lu is the largest prime number it found, %lu was the largest number it searched.\n",
            getpid(), getHighestPrime(self), getHighestSearch(self));
            
        PrimeSearch_destroy(self);
        exit(0);
    }
}

struct PrimeSearch* PrimeSearch_create() {
    // Register signals
    signal(SIGTSTP, signalHandler);
    signal(SIGCONT, signalHandler);
    signal(SIGTERM, signalHandler);

    // Allocate memory for the searcher
    struct PrimeSearch* new_searcher = (struct PrimeSearch*)malloc(sizeof(struct PrimeSearch));

    // Generate a random 10-digit starting value
    srand(time(NULL));
    unsigned long starting_value = 1000000000 + (rand() % 9000000000);

    // Initialize the searcher with the generated starting value
    if (new_searcher != NULL) {
        new_searcher->current_highest_search = starting_value;
        new_searcher->current_highest_prime = 2;
    }
    else { // Handle error during creation
        fprintf(stderr, "There was an error while creating PrimeSearch object\n");
        exit(1);
    }

    return new_searcher;
}

unsigned long getHighestSearch(struct PrimeSearch* obj) {
    return obj->current_highest_search;
}

unsigned long getHighestPrime(struct PrimeSearch* obj) {
    return obj->current_highest_prime;
}

void checkNext(struct PrimeSearch* obj) {
    unsigned long number = obj->current_highest_search + 1;
    int prime_flag = 1;
    
    // Number is prime if not divisible by numbers between 2 and n/2
    for (unsigned long i = 2; i <= number/2; i++) {
        // If number is not prime, lower the prime flag and stop checking
        if (number % i == 0) {
            prime_flag = 0;
            break;
        }
    }

    // Update the struct with new highest search and prime
    obj->current_highest_search = number;
    if (prime_flag) { obj->current_highest_prime = number; }
}

void PrimeSearch_destroy(struct PrimeSearch* obj) {
    free(obj);
}


int main() {
    self = PrimeSearch_create();

    // When the process is first started
    fprintf(stdout, "PID %d is beginning search, starting with value %lu\n", getpid(), getHighestSearch(self));

    // While the proc is scheduled, it will search for primes
    while(!terminated) {
        while(searching) {
            checkNext(self);
        }

        // While paused, just sleep
        sleep(1);
    }
}