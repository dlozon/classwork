#include "Arduino.h"
#include <Motor.h>

Motor motorLeft(8, 5);
Motor motorRight(12, 6);

// Turn the robot 90 degrees to its right
void turnClockwise90() {
    motorLeft.setSpeed(50);
    motorRight.setSpeed(-50);
    delay(430);

    motorLeft.activeStop();
    motorRight.activeStop();
}

// Drive the robot forward 4ft
void driveForward4ft50Percent() {
    motorLeft.forward(48);
    motorRight.forward(50);
    delay(3100);

    motorLeft.activeStop();
    motorRight.activeStop();
}

// Drive the robot forward 4ft
void driveForward4ft80Percent() {
    motorLeft.forward(76);
    motorRight.forward(80);
    delay(1969);

    motorLeft.activeStop();
    motorRight.activeStop();
}


// Configure motors and wait a second before starting the program
void setup() {
    motorLeft.setInverted(true);
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