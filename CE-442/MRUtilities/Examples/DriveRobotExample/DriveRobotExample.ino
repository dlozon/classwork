#include "Arduino.h"
#include <Motor.h>

/* Initialize the left-side motor
 * Pin 5 is an analog pin used to control the speed of the motor
 * Pin 8 is a digital pin used to control the direction of the motor */
Motor motorLeft(5, 8);
/* Initialize the right-side motor
 * Pin 6 is an analog pin used to control the speed of the motor
 * Pin 12 is a digital pin used to control the direction of the motor */
Motor motorRight(6, 12);

void setup() {
  /* Invert the direction of the left motor 
   * We do this because the left motor is facing the opposite direction of the right motor.
   * That means left's forward is right's backward. */
  motorLeft.setInverted(true);

  /* Wait 3 seconds before starting the program so you can get out of the way. */
  delay(3000);
}

void loop()
{
  // Drive robot forward for 2 seconds at 80% speed
  motorLeft.forward(80);
  motorRight.forward(80);
  delay(2000);

  // Drive robot backward for 2 seconds at 80% speed
  motorLeft.backward(80);
  motorRight.backward(80);
  delay(2000);

  // Stop robot for 1 second (notice how it rolls to a stop)
  motorLeft.stop();
  motorRight.stop();
  delay(1000);
  
  // Turn robot in the clockwise direction (right) for 1 second
  motorLeft.forward(50);
  motorRight.backward(50);
  delay(1000);
  
  // Turn robot in the counter-clockwise direction (left) for 1 second
  motorLeft.backward(50);
  motorRight.forward(50);
  delay(1000);

  // Stop robot for 1 second (notice how it stops immediately)
  motorLeft.activeStop();
  motorRight.activeStop();
  delay(1000);
}
