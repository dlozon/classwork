#include <stdio.h>
#include <conio.h>
#include <time.h>
#include <math.h>
#include <windows.h>
#include <string.h>

/** Converts ms to min:sec.ms */
void displayTime(float milliseconds) {
    // Derive the min and sec from milliseconds
    float seconds = milliseconds / 1000;
    int minutes = (int)seconds / 60;

    // Modulo seconds by 60, so that 61 seconds becomes 1 minute 1 second
    seconds = fmod(seconds, 60);

    // Print the resulting formatted time
    printf("%d:%04.1f", minutes, seconds);
}

int main() {
    printf("Initializing...\n");
    // Init code goes here
    Sleep(1000); // Wait for 1 second

    /** Whether the user has been prompted for input yet*/
    int printedW = 0;
    /** Whether the stopwatch is currently active */
    int timing = 0;
    /** The starting stopwatch time */
    clock_t startTime = 0;
    float elapsedTime = 0.0;

    while (1) {
        // Quit when escape is pressed
        if (GetAsyncKeyState(VK_ESCAPE)) { // Escape key
            printf("Shutting down...\n");
            break;
        }

        // Print current time when B is pressed
        if (GetAsyncKeyState('B') && timing) {
            printf("So far ");
            displayTime(elapsedTime);
            printf(" has passed.\n");
        }

        // Toggle the timer when CTRL is pressed
        if (GetAsyncKeyState(VK_CONTROL)) {
            if (timing) {
                // Stop the timer and print total time
                timing = 0;
                printf("The total time was: ");
                displayTime(elapsedTime);
                printf("\n\n");
            } 
            else {
                // Start and reset the timer
                timing = 1;
                printf("Timer Start\n");
                startTime = clock();
                printedW = 0; // Reset printedW so it can be printed again
            }
        }
        else if(!timing && !printedW) {
            // Prompt the user for input if you haven't already
            printf("Waiting for user input...\n");
            printedW = 1;
        }

        // Update the current time and sleep
        elapsedTime = clock() - startTime;
        Sleep(100);
    }

    return 0;
}
