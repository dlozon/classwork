#include <stdio.h>
#include <conio.h>
#include <time.h>
#include <windows.h>
#include <string.h>

/** The time in ms it takes to pass through each state in the main loop */
const int cycleDuration = 12000; 

/** The time in ms it takes to pass through each state in the night mode loop */
const int nightModeCycleDuration = 3000;

/** This represents all possible traffic light display states */
typedef enum {
    INIT,
    GREEN_NS,
    YELLOW_NS,
    RED_NS_GREEN_EW,
    RED_NS_YELLOW_EW,
    NIGHT_MODE_R,
    NIGHT_MODE_OFF,
    SHUTDOWN
} TrafficLightState;

/** Clear the console and print a string */
void updateConsole(const char* str) {
    system("cls"); // clear the console
    printf("%s\n", str); // print a string
}

/** Print out the traffic light colors associated with a particular TrafficLightState */
void printState(TrafficLightState state) {
    switch (state) {
            case INIT:
                updateConsole("I\n");
                break;
            case GREEN_NS:
                updateConsole("DAY MODE\nN/S  E/W\n G    R");
                break;
            case YELLOW_NS:
                updateConsole("DAY MODE\nN/S  E/W\n Y    R");
                break;
            case RED_NS_GREEN_EW:
                updateConsole("DAY MODE\nN/S  E/W\n R    G");
                break;
            case RED_NS_YELLOW_EW:
                updateConsole("DAY MODE\nN/S  E/W\n R    Y");
                break;
            case NIGHT_MODE_R:
                updateConsole("NIGHT MODE\n N/S  E/W \n  R    R  ");
                break;
            case NIGHT_MODE_OFF:
                updateConsole("NIGHT MODE\n N/S  E/W \n OFF  OFF ");
                break;
            case SHUTDOWN:
                updateConsole("Shutting Down...");
                break;
        }
}


int main() {

    int isInitializing = 1;
    int nightModeIsActive = 0;

    int startTime = clock(); 
    int elapsedTime = 0; 

    // prevstate can't start as init because of anti-flicker
    TrafficLightState prevState = SHUTDOWN;
    TrafficLightState currState = INIT;

    while (1) {

        if (isInitializing && elapsedTime < 1000) {
            // any initialization code would go here
            // since this is simulating an init, we just update the clock
            elapsedTime = (clock() - startTime);
        }
        else if (isInitializing && elapsedTime > 1000) {
            // after 1 sec of initializing, end init and reset timer
            isInitializing = 0;
            startTime = clock();
        }
        else if (!nightModeIsActive) {
            if (elapsedTime < 4000) 
                currState = RED_NS_GREEN_EW; // 0-4 seconds
            if (elapsedTime >= 4000 && elapsedTime < 6000) 
                currState = RED_NS_YELLOW_EW; // 4-6 seconds
            if (elapsedTime >= 6000 && elapsedTime < 10000) 
                currState = GREEN_NS; // 6-10 seconds
            if (elapsedTime >= 10000) 
                currState = YELLOW_NS; // 10-12 seconds

            elapsedTime = (clock() - startTime) % cycleDuration;
        }
        else {
            if (elapsedTime < 1500) 
                currState = NIGHT_MODE_R; // 0-1.5 seconds
            else
                currState = NIGHT_MODE_OFF; // 1.5-3 seconds

            elapsedTime = (clock() - startTime) % nightModeCycleDuration;
        }

        // only update the console if the state has been changed
        // prevents flickering present when updating 10 times per sec
        if (prevState != currState)
            printState(currState);

        // update the state and sleep
        prevState = currState;
        Sleep(100);

        // Activate shutdown when ESCAPE is pressed
        if (GetAsyncKeyState(VK_ESCAPE)) { 
            currState = SHUTDOWN;
            printState(currState);
            break;
        } 
        // Toggle night mode when CTRL is pressed
        else if (GetAsyncKeyState(VK_CONTROL)) {
            nightModeIsActive = !nightModeIsActive; 
            startTime = clock(); // reset timer for consistency
        }
    }

    return 0;
}