#include "Arduino.h"
#include <DifferentialDrive.h>

DifferentialDrive drivetrain(5, 8, 6, 12);

// Drive in a right-side circle with a 4ft diameter
void clockwiseCircle4ftDiameter() {
    // Drive in a circle
    drivetrain.tankDrive(70, 54);
    delay(8015);
    // Brake
    drivetrain.activeStop();
}

// Drive in a left-side circle with a 4ft diameter
void counterClockwiseCircle4ftDiameter() {
    // Drive in a circle
    drivetrain.tankDrive(50, 70);
    delay(7915);
    // Brake
    drivetrain.activeStop();
}

// Wait a second before starting the program
void setup() {
    delay(1000);
}

// Drive in a figure-eight pattern with 4ft diameter circles
void loop() {
    clockwiseCircle4ftDiameter();
    delay(500);
    
    counterClockwiseCircle4ftDiameter();
    delay(500);
}