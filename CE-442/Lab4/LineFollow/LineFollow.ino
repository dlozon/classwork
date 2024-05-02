#include "Arduino.h"
#include <DifferentialDrive.h>
#include <NewPing.h>
#include <Wire.h>

DifferentialDrive drivetrain(5, 8, 6, 12);
NewPing sonar(A2, A3, 50);

int line_sensor[5]; // Array for storing line sensor readings


// Read the 5 line sensors
void readLineSensors() {
    int count = 0;
    byte high_byte, low_byte;
    byte raw_result[10];

    // Request 10 bytes from I2C device address 0x11
    Wire.requestFrom(0x11, 10);
    while (Wire.available()) {
        raw_result[count] = Wire.read();
        count += 1;
    }
    // Combine the low & high bytes
    for (int i = 0; i < 5; i++) {
        high_byte = raw_result[i * 2] << 8;
        low_byte = raw_result[i * 2 + 1];
        line_sensor[i] = high_byte + low_byte;
    }
}

// Attempt to drive forward while staying centered on a line
void followLine() {
    float centerSensorWeight = 1.25;
    float outerSensorWeight = 4;
    float driveScalar = 1.75;
    float turnScalar = .15;

    // Calculate the turning instruction by finding the difference between the left and right sensors
    int turnSpeed = (outerSensorWeight * line_sensor[4] + line_sensor[3]) - (line_sensor[1] + line_sensor[0] * outerSensorWeight);
    // Go faster when the center sensors are more over the line
    int driveSpeed = line_sensor[1] + line_sensor[2] * centerSensorWeight + line_sensor[3];

    // Send instructions to the drivetrain
    drivetrain.arcadeDrive(driveSpeed * driveScalar, turnSpeed * turnScalar);    
}


void setup() {
    Wire.begin(); // Join i2c bus
    Serial.begin(9600); // Start serial communication    
    delay(1000);
}

void loop() {
    int iteration = 0;
    int obstacleDistance = 0;

    if (!(iteration % 5)) // Only read the sonar every 5th iteration
        obstacleDistance = sonar.convert_cm(sonar.ping_median());
    readLineSensors(); // Read the line sensors

    // Stop the robot if there is an obstacle
    if (obstacleDistance && obstacleDistance < 30)
        drivetrain.activeStop();
    else // Drive on the line if there is not
        followLine();

    iteration++;
    delay(10);    
}