/*  Class for driving a dc motor using the L298N H-Bridge.
 *  Created by Dylan Lozon, June 2024. */
#ifndef MOTOR_H
#define MOTOR_H

#include "Arduino.h"

class Motor {
public:
    /** @brief Constructor for the Motor class.
     *  @param clockwisePin The digital pin that enables clockwise rotation.
     *  @param counterClockwisePin The digital pin that enables counterclockwise rotation.
     *  @param speedPin The analog pin that controls the speed of the motor.
     *  @param inverted Whether the motor direction should be inverted. */
    Motor(int clockwisePin, int counterClockwisePin, int speedPin, bool inverted = false);


    /** @brief Get the inversion state of the motor.
     *  @return Whether the motor direction is being inverted. */
    bool getInverted();
    /** @brief Set the inversion state of the motor.
     *  @param invert Whether the motor direction should be inverted. */
    void setInverted(bool invert);

    /** @brief Sets the speed of the motor where negative values drive in reverse.
     *  @param speed The speed value to set [-100, 100]. */
    void setSpeed(double speed);
    /** @brief Drives the motor forward.
     *  @param speed The speed value to set [0, 100]. */
    void forward(double speed);
    /** @brief Drives the motor in reverse.
     *  @param speed The speed value to set [0, 100]. */
    void backward(double speed);

    /** @brief Stops sending power to the motor. */
	void stop();
    /** @brief Provides a very small amount of power to the motor to stop it from turning. */
    void activeStop();
  
  
private:
    int spdPin; // The analog pin connected to the speed pin of the motor.
    int cwPin; // The digital pin connected to the clockwise pin of the h-bride.
    int ccwPin; // The digital pin connected to the counterclockwise pin of the h-bridge.
    bool inverted; // Whether the motor direction is inverted.

    /** @brief Sends power to the motor.
     *  @param dir True for clockwise movement, false for counterclockwise.
     *  @param speed The disired percent speed of the motor. */
    void sendPower(bool dir, double speed);
};

#endif
