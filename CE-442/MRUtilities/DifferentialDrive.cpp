#include "Arduino.h"
#include "Motor.h"
#include "DifferentialDrive.h"


DifferentialDrive::DifferentialDrive(Motor* leftMotor, Motor* rightMotor) {
    this->leftMotor = leftMotor;
    leftMotor->setInverted(true);
    
    this->rightMotor = rightMotor;
}
DifferentialDrive::DifferentialDrive(int lmSpdPin, int lmDirPin, int rmSpdPin, int rmDirPin) {
    leftMotor = new Motor(lmSpdPin, lmDirPin);
    leftMotor->setInverted(true);

    rightMotor = new Motor(rmSpdPin, rmDirPin);
}


void DifferentialDrive::arcadeDrive(int drive, int turn) {
    // Limit inputs to [-100, 100]
    drive = drive < -100 ? -100 : 
           (drive > 100 ? 100 : drive);
    turn =  turn < -100 ? -100 : 
            (turn > 100 ? 100 : turn);

    // Calculate the instructions for each motor
    int leftSpeed = drive + turn;
    int rightSpeed = drive - turn;

    // Only attempt to scale if at least one input is non-zero
    if (drive || turn) {
        // Find the factor required to scale the largest input down to 100
        int greaterInput = max(abs(drive), abs(turn));
        int lesserInput = min(abs(drive), abs(turn));
        int saturatedInput = (greaterInput + lesserInput) / greaterInput;

        // Scale the wheel speeds such that neither is over 100
        leftSpeed /= saturatedInput;
        rightSpeed /= saturatedInput;
    }

    leftMotor->setSpeed(leftSpeed);
    rightMotor->setSpeed(rightSpeed);
}
void DifferentialDrive::tankDrive(int leftSpeed, int rightSpeed) {
    leftMotor->setSpeed(leftSpeed);
    rightMotor->setSpeed(rightSpeed);
}

void DifferentialDrive::forward(int speed) {
    leftMotor->forward(speed);
    rightMotor->forward(speed);
}
void DifferentialDrive::backward(int speed) {
    leftMotor->backward(speed);
    rightMotor->backward(speed);
}
void DifferentialDrive::turnLeft(int speed) {
    leftMotor->backward(speed);
    rightMotor->forward(speed);
}
void DifferentialDrive::turnRight(int speed) {
    leftMotor->forward(speed);
    rightMotor->backward(speed);
}

void DifferentialDrive::stop() {
    leftMotor->stop();
    rightMotor->stop();
}
void DifferentialDrive::activeStop() {
    leftMotor->activeStop();
    rightMotor->activeStop();
}