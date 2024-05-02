#include "Arduino.h"
#include <DifferentialDrive.h>

DifferentialDrive drivetrain(5, 8, 6, 12);

// Turn the robot 90 degrees to its right
void turnClockwise90() {
    drivetrain.turnRight(50);
    delay(430);

    drivetrain.activeStop();
}

// Drive the robot forward 4ft
void driveForward4ft50Percent() {
    drivetrain.tankDrive(49, 50);
    delay(3100);

    drivetrain.activeStop();
}

// Drive the robot forward 4ft
void driveForward4ft80Percent() {
    drivetrain.tankDrive(77, 80);
    delay(1969);

    drivetrain.activeStop();
}


// Configure motors and wait a second before starting the program
void setup() {
    delay(1000);
}

void loop() {
    // Drive in a square at 50% speed
    for (int i = 0; i < 4; i++) {
        driveForward4ft50Percent();
        delay(250);
        turnClockwise90();
        delay(250);
    }

    delay(2500); // Wait between squares

    // Drive in a square at 80% speed
    for (int i = 0; i < 4; i++) {
        driveForward4ft80Percent();
        delay(250);
        turnClockwise90();
        delay(250);
    }

    delay(2500); // Wait between squares
}