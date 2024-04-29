/*
  Motor.h - Library for driving a dc motor using the Adafruit Adafruit TB6612 motor driver board.
  Link to the motor driver board: 
  https://learn.adafruit.com/adafruit-tb6612-h-bridge-dc-stepper-motor-driver-breakout/overview
  
  Created by Li Dang and Girma Tewolde, October 13, 2015.
  Modified by Dylan Lozon, April 19, 2024.
  Released into the public domain.
*/
#ifndef Motor_h
#define Motor_h

#include "Arduino.h"

class Motor {
public:
    /** @brief Constructor for the Motor class.
     *  @param d_pin The digital pin connected to the direction pin of the motor.
     *  @param s_pin The analog pin connected to the speed pin of the motor. */
    Motor(int d_pin, int s_pin);


    /** @brief Get the inversion state of the motor.
     *  @return Whether the motor direction is being inverted. */
    bool getInverted();
    /** @brief Set the inversion state of the motor.
     *  @param invert Whether the motor direction should be inverted. */
    void setInverted(bool invert);

    /** @brief Sets the speed of the motor where negative values drive in reverse.
     *  @param speed The speed value to set [-100, 100]. */
    void setSpeed(int speed);
    /** @brief Drives the motor forward.
     *  @param speed The speed value to set [0, 100]. */
    void forward(int speed);
    /** @brief Drives the motor in reverse.
     *  @param speed The speed value to set [0, 100]. */
    void backward(int speed);

    /** @brief Stops sending power to the motor. */
	void stop();
    /** @brief Provides a very small amount of power to the motor to stop it from turning. */
    void activeStop();
  
private:
    // The digital pin connected to the direction pin of the motor.
    int dir_pin;
    // The analog pin connected to the speed pin of the motor.
    int speed_pin;
    // Whether the motor direction is inverted.
    bool inverted;
};

#endif
