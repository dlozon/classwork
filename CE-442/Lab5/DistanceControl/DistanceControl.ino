#include "Arduino.h"
#include <DifferentialDrive.h>
#include <NewPing.h>

#define P 3
#define TARGET_DISTANCE 25

DifferentialDrive drivetrain(5, 8, 6, 12);
NewPing sonar(A2, A3, 100);


void setup() {
    delay(1000);
}

void loop() {
    int objectDistance = sonar.convert_cm(sonar.ping_median());
    double error = objectDistance - TARGET_DISTANCE;

    if (objectDistance)
        drivetrain.arcadeDrive(error * P, 0);
    else 
        drivetrain.forward(100);

    delay(50);
}
