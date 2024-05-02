#ifndef DIFFERENTIAL_DRIVE_H
#define DIFFERENTIAL_DRIVE_H

#include <Arduino.h>
#include <Motor.h>

/** @brief The DifferentialDrive class represents a differential drive system.
 * It provides methods to control the movement of the robot using two motors. 
 * The left motor is assumed to be facing the opposite direction of the right motor. */
class DifferentialDrive {
public:
    /** @brief Constructs a DifferentialDrive object with the given left and right motors.
     *  @param leftMotor A pointer to the left motor object.
     *  @param rightMotor A pointer to the right motor object. */
    DifferentialDrive(Motor* leftMotor, Motor* rightMotor);\
    /** @brief Constructs a DifferentialDrive object with the given pin numbers for motor control.
     *  @param leftMotorSpeedPin The pin number for the left motor speed control.
     *  @param leftMotorDirectionPin The pin number for the left motor direction control.
     *  @param rightMotorSpeedPin The pin number for the right motor speed control. 
     *  @param rightMotorDirectionPin The pin number for the right motor direction control. */
    DifferentialDrive(int leftMotorSpeedPin, int leftMotorDirectionPin, int rightMotorSpeedPin, int rightMotorDirectionPin);


    /** @brief Drives the robot using arcade drive control.
     *  @param drive The speed of forward/backward movement. Positive values move forward, negative values move backward.
     *  @param turn The speed of left/right turning. Positive values turn right, negative values turn left. */
    void arcadeDrive(int drive, int turn);
    /** @brief Drives the robot using tank drive control.
     *  @param leftSpeed The speed of the left motor. Positive values move forward, negative values move backward.
     *  @param rightSpeed The speed of the right motor. Positive values move forward, negative values move backward. */
    void tankDrive(int leftSpeed, int rightSpeed);

    /** @brief Drives the robot forward at the specified speed.
     *  @param speed The speed of forward movement. Positive values move forward. */
    void forward(int speed);
    /** @brief Drives the robot backward at the specified speed.
     *  @param speed The speed of backward movement. Positive values move backward. */
    void backward(int speed);
    /** @brief Turns the robot left at the specified speed.
     *  @param speed The speed of left turning. Positive values turn left. */
    void turnLeft(int speed);
    /** @brief Turns the robot right at the specified speed.
     *  @param speed The speed of right turning. Positive values turn right. */
    void turnRight(int speed);

    /** @brief Stops the robot by cutting power to the motors. */
    void stop();
    /** @brief Stops the robot by providing power to actively resist movement. */
    void activeStop();


private:
    Motor* leftMotor;   // Pointer to the left motor object.
    Motor* rightMotor;  // Pointer to the right motor object.
};

#endif
