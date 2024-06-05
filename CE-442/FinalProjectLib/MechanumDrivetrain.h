#ifndef MECHANUM_DRIVETRAIN_H
#define MECHANUM_DRIVETRAIN_H

#include "Arduino.h"
#include <Motor.h>

#define MOTOR_COUNT 4

class MechanumDrivetrain {
public:
    /** @brief Constructs a DifferentialDrive object with the given left and right motors.
     *  @param frontLeft A pointer to the front left motor object.
     *  @param frontRight A pointer to the front right motor object.
     *  @param rearLeft A pointer to the rear left motor object.
     *  @param rearRight A pointer to the rear right motor object. */
    MechanumDrivetrain(Motor* frontLeft, Motor* frontRight, Motor* rearLeft, Motor* rearRight);


    /** @brief A generalized function to drive the robot. 
     *  @param x The percent speed of longitudinal movement.
     *  @param y The percent speed of lateral movement.
     *  @param rotation The percent speed of rotation around the vertical axis. */
    void drive(double x, double y, double rotation);

    /** @brief Drives the robot forward.
     *  @param speed The percent speed of movement. */
    void forward(double speed);
    /** @brief Drives the robot backward.
     *  @param speed The percent speed of movement. */
    void backward(double speed);
    /** @brief Drives the robot to its right.
     *  @param speed The percent speed of movement. */
    void right(double speed);
    /** @brief Drives the robot to its left.
     *  @param speed The percent speed of movement. */
    void left(double speed);
    /** @brief Spins the robot clockwise.
     *  @param speed The percent speed of rotation. */
    void turnRight(double speed);
    /** @brief Spins the robot counter-clockwise.
     *  @param speed The percent speed of rotation. */
    void turnLeft(double speed);

    /** @brief Cuts power to the motors. */
    void stop();
    /** @brief Actively provides power to stop the motors. */
    void activeStop();

private:
    // Pointers to each of the motors
    Motor* motor[MOTOR_COUNT];
};

#endif