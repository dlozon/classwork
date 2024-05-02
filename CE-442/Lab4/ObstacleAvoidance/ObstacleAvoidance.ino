#include "Arduino.h"
#include <DifferentialDrive.h>
#include <NewPing.h>

DifferentialDrive drivetrain(5, 8, 6, 12);
NewPing sonar(A2, A3, 50);


// Turn the robot 90 degrees to its right
void turnClockwise90() {
    drivetrain.turnRight(50);
    delay(430);

    drivetrain.activeStop();
}
// Turn the robot 90 degrees to its left
void turnCounterClockwise90() {
    drivetrain.turnLeft(50);
    delay(430);

    drivetrain.activeStop();
}


void setup() {
    delay(1000);
}

void loop() {
    int obstacleDistance = sonar.convert_cm(sonar.ping_median());

    // If there is an obstacle within 30cm, turn to avoid it
    if (obstacleDistance && obstacleDistance < 30) {
        if(random(0,2) == 0)
            turnClockwise90();
        else
            turnCounterClockwise90();
    }

    drivetrain.tankDrive(49, 50);
    delay(50);
}