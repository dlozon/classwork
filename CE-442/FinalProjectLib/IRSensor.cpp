#include "Arduino.h"
#include "IRSensor.h"


IRSensor::IRSensor(int dataPin) {
    pinMode(dataPin, OUTPUT);
    this->pin = dataPin;
}


bool IRSensor::isBlocked() { return !digitalRead(pin); }
bool IRSensor::isOpen() { return digitalRead(pin); }