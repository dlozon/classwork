#include <stdio.h>
#include <Windows.h>

/** How far the sensor can read from */
#define MAX_DISTANCE 100   
/** How much the sensor simulation changes with each press */
#define STEP_SIZE 5        

/** Enum of robot's possible states */
enum RobotState {
    INIT,
    IDLE,
    FORWARD,
    BACKWARD,
    SHUTDOWN
};

/** Passes the robot's data around the program */
struct RobotDataBus {
    enum RobotState currentState;
    int CurrentDistance;
};

/** Print the robot's state and sensor reading to the console*/
void printStatus(struct RobotDataBus* dataBus) {
    // Long ternary statement to convert current state to a readable string
    printf("Current State: %s\n", dataBus->currentState == INIT ? "Initialize" :
                                   dataBus->currentState == IDLE ? "Idle" :
                                   dataBus->currentState == FORWARD ? "Forward" :
                                   dataBus->currentState == BACKWARD ? "Backward" : "Shutdown");
    // Print current distance from target
    printf("Sensor Reading: %d\n\n", dataBus->CurrentDistance);
}

/** Update the state of the robot based on the sensor's current reading */
void updateState(struct RobotDataBus* dataBus) {
    switch (dataBus->currentState) {
        case INIT:
            // Init code goes here
            Sleep(1000);
            printf("I\n");
            dataBus->currentState = IDLE;
            break;

        case IDLE: // Idle when you are at the correct distance
            // Once sensor reads more than 50, you transition to the forward state
            if (dataBus->CurrentDistance > 50) {
                printf("Moving forward...\n\n");
                dataBus->currentState = FORWARD;
            } 
            // Once sensor reads less than 30, you transition to the backward state
            else if (dataBus->CurrentDistance < 30) {
                printf("Moving backward...\n\n");
                dataBus->currentState = BACKWARD;
            }
            break;

        case FORWARD: // Go forwards while you are too far
            // Once sensor reads 50 or less, you can transition to the idle state
            if (dataBus->CurrentDistance <= 50) {
                printf("Returning to idle state...\n\n");
                dataBus->currentState = IDLE;
            }
            break;

        case BACKWARD: // Go backwards while you are too close
            // Once sensor reads 30 or more, you can transition to the idle state
            if (dataBus->CurrentDistance >= 30) {
                printf("Returning to idle state...\n\n");
                dataBus->currentState = IDLE;
            }
            break;

        case SHUTDOWN: // Shut down the robot
            printf("Shutting down...\n");
            // Shutdown code goes here
            break;
    }
}


/** Simulate the sensor by manually adjusting its "reading" */
void adjustSensorReading(struct RobotDataBus* dataBus, int step) {
    dataBus->CurrentDistance += step;

    // Force the sensor reading within bounds [0,100]
    if (dataBus->CurrentDistance < 0)
        dataBus->CurrentDistance = 0;
    else if (dataBus->CurrentDistance > MAX_DISTANCE)
        dataBus->CurrentDistance = MAX_DISTANCE;
}

int main() {
    struct RobotDataBus robotDataBus;
    robotDataBus.currentState = INIT;
    robotDataBus.CurrentDistance = 50;

    while (robotDataBus.currentState != SHUTDOWN) {
        // Escape closes the program
        if (GetAsyncKeyState(VK_ESCAPE)) { 
            robotDataBus.currentState = SHUTDOWN;
            printStatus(&robotDataBus);
        } 
        // Right Arrow increases sensor reading
        else if (GetAsyncKeyState(VK_RIGHT)) {
            adjustSensorReading(&robotDataBus, STEP_SIZE);
            printStatus(&robotDataBus);
        } 
        // Left Arrow increases sensor reading
        else if (GetAsyncKeyState(VK_LEFT)) {
            adjustSensorReading(&robotDataBus, -STEP_SIZE);
            printStatus(&robotDataBus);
        }

        // Update the robot's state and sleep
        updateState(&robotDataBus);
        Sleep(100);
    }

    return 0;
}
