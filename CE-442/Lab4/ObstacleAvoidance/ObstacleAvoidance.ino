#include "Arduino.h"
#include <Motor.h>
#include <NewPing.h>

Motor motorLeft(8, 5);
Motor motorRight(12, 6);
NewPing sonar(A2, A3, 50);
void setup() {
    motorLeft.setInverted(true);
    delay(1000);
}

void loop() {
    int obstacleDistance = sonar.convert_cm(sonar.ping_median());

    // If there is no obstacle within 30cm, drive forward
    if (!obstacleDistance || obstacleDistance > 30) {
        motorLeft.forward(49);
        motorRight.forward(50);
    }
    else { // Turn clockwise to avoid obstacles
        if (random(0,1) == 1) {
            motorLeft.backward(50);
            motorRight.forward(50);
            while(obstacleDistance || obstacleDistance > 30){
            obstacleDistance = sonar.convert_cm(sonar.ping_median());  
            delay(50);
            }
        }
        else {
            motorLeft.forward(50);
            motorRight.backward(50);
            while(obstacleDistance || obstacleDistance > 30){
                obstacleDistance = sonar.convert_cm(sonar.ping_median());  
                delay(50);
            }
        }
    } 

    delay(50);
}