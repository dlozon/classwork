#include "Arduino.h"
#include "Motor.h"
#include "MechanumDrivetrain.h"


MechanumDrivetrain::MechanumDrivetrain(Motor* fl, Motor* fr, Motor* rl, Motor* rr) {
    motor[0] = fl;
    motor[1] = fr;
    motor[2] = rl;
    motor[3] = rr;
}


void MechanumDrivetrain::drive(double x, double y, double rot) {
    // Force the speeds within the bounds of [-1,1]
    x = constrain(x, -1, 1);
    y = constrain(y, -1, 1);
    rot = constrain(rot, -1, 1);

    double speed[MOTOR_COUNT];
    double greatest;

    // Calculate the speed of each wheel
    speed[0] = x + y + rot; 
    speed[1] = x - y - rot; 
    speed[2] = x - y + rot; 
    speed[3] = x + y - rot; 
    // Find the greatest wheel speed
    greatest = abs(speed[0]); 
    for (int i = 1; i < MOTOR_COUNT; i++) {
        greatest = max(abs(speed[i]), greatest);
    }

    // Normalize the wheel speeds if needed
    if (greatest > 1) { 
        for (int i = 0; i < MOTOR_COUNT; i++) {
            speed[i] /= greatest;
        }
    }

    // Send the calculated instructions to the motors
    for (int i = 0; i < MOTOR_COUNT; i++) {
        motor[i]->setSpeed(speed[i]);
    }
}

void MechanumDrivetrain::forward(double speed) { drive(speed, 0, 0); }
void MechanumDrivetrain::backward(double speed) { drive(-speed, 0, 0); }
void MechanumDrivetrain::right(double speed) { drive(0, speed, 0); }
void MechanumDrivetrain::left(double speed) { drive(0, -speed, 0); }
void MechanumDrivetrain::turnRight(double speed) { drive(0, 0, speed); }
void MechanumDrivetrain::turnLeft(double speed) { drive(0, 0, -speed); }

void MechanumDrivetrain::stop() { 
    for (int i = 0; i < MOTOR_COUNT; i++) {
        motor[i]->stop();
    }
}
void MechanumDrivetrain::activeStop() {
    for (int i = 0; i < MOTOR_COUNT; i++) {
        motor[i]->activeStop();
    }
}