#include "Arduino.h"
#include <DifferentialDrive.h>

DifferentialDrive drivetrain(5, 8, 6, 12);

void setup() {
  /* Wait 3 seconds before starting the program so you can get out of the way. */
  delay(3000);
}

void loop() {
    // Drive robot forward for 2 seconds at 80% speed
    drivetrain.forward(80);
    delay(2000);

    // Drive robot backward for 2 seconds at 80% speed
    drivetrain.backward(80);
    delay(2000);

    // Stop robot for 1 second (notice how it rolls to a stop)
    drivetrain.stop();
    delay(1000);
    
    // Turn robot in the clockwise direction (right) for 1 second
    drivetrain.turnRight(50);
    delay(1000);
    
    // Turn robot in the counter-clockwise direction (left) for 1 second
    drivetrain.turnLeft(50);
    delay(1000);

    // Stop robot for 1 second (notice how it stops immediately)
    drivetrain.activeStop();
    delay(1000);
}
