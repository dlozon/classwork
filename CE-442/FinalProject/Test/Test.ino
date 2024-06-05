#include "Arduino.h"
#include <Motor.h>
#include <MechanumDrivetrain.h>

Motor frontLeft(12, 13, 10);
Motor frontRight(7, 6, 3);
Motor rearLeft(4, 2, 5);
Motor rearRight(8, 9, 11);
MechanumDrivetrain drivetrain(&frontLeft, &frontRight, &rearLeft, &rearRight);

void setup() {
    delay(300);
}

void test() {
    drivetrain.backward(.5);
    delay(2000);
    drivetrain.stop();
    delay(500);
}

void loop() {
    // Drive forward
    drivetrain.forward(.5);
    delay(2000);
    drivetrain.stop();
    delay(500);

    // Drive backward
    drivetrain.backward(.5);
    delay(2000);
    drivetrain.stop();
    delay(500);

    // Drive left
    drivetrain.left(.5);
    delay(2000);
    drivetrain.stop();
    delay(500);

    // Drive right
    drivetrain.right(.5);
    delay(2000);
    drivetrain.stop();
    delay(500);

    // Drive diagonally forward-left
    drivetrain.drive(0.5, -0.5, 0);
    delay(2000);
    drivetrain.stop();
    delay(500);

    // Drive diagonally backward-right
    drivetrain.drive(-0.5, 0.5, 0);
    delay(2000);
    drivetrain.stop();
    delay(500);

    // Drive diagonally forward-right
    drivetrain.drive(0.5, 0.5, 0);
    delay(2000);
    drivetrain.stop();
    delay(500);

    // Drive diagonally backward-left
    drivetrain.drive(-0.5, -0.5, 0);
    delay(2000);
    drivetrain.stop();
    delay(500);

    // Spin clockwise
    drivetrain.turnRight(.5);
    delay(2000);
    drivetrain.stop();
    delay(500);

    // Spin counterclockwise
    drivetrain.turnLeft(.5);
    delay(2000);

    // Stop the robot
    drivetrain.stop();
    delay(2000);
}