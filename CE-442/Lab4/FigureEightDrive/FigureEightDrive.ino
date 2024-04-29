#include "Arduino.h"
#include <Motor.h>

Motor motorLeft(8, 5);
Motor motorRight(12, 6);

// Drive in a right-side circle with a 4ft diameter
void clockwiseCircle4ftDiameter() {
    // Drive in a circle
    motorLeft.forward(70);
    motorRight.forward(54);
    delay(8015);
    // Brake
    motorLeft.activeStop();
    motorRight.activeStop();
}

// Drive in a left-side circle with a 4ft diameter
void counterClockwiseCircle4ftDiameter() {
    // Drive in a circle
    motorLeft.forward(50);
    motorRight.forward(70);
    delay(7915);
    // Brake
    motorLeft.activeStop();
    motorRight.activeStop();
}

// Configure motors and wait a second before starting the program
void setup() {
    motorLeft.setInverted(true);
    delay(1000);
}

// Drive in a figure-eight pattern with 4ft diameter circles
void loop() {
    clockwiseCircle4ftDiameter();
    delay(1000);
    
    counterClockwiseCircle4ftDiameter();
    delay(1000);
}