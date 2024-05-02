#include "Arduino.h"
#include "Motor.h"


Motor::Motor(int dirPin, int spdPin, bool inverted) {
	pinMode(dirPin, OUTPUT);
	pinMode(spdPin, OUTPUT);
	this->dirPin = dirPin;
	this->spdPin = spdPin;
	this->inverted = inverted; // Defaults to false
}


void Motor::setInverted(bool invert) { inverted = invert; }
bool Motor::getInverted() { return inverted; }

void Motor::setSpeed(int speed) {
	// If speed is negative, drive in reverse.
	bool dir = (speed <= 0 ? LOW : HIGH);
	// If inverted, invert the direction.
	dir = inverted ? !dir : dir;

	// Drive the motor
	digitalWrite(dirPin, dir);
	analogWrite(spdPin, map(speed, 0, 100, 0, 255));
}

void Motor::forward(int speed) {
	digitalWrite(dirPin, inverted ? LOW : HIGH);
	analogWrite(spdPin, map(speed, 0, 100, 0, 255));
}

void Motor::backward(int speed) {
	digitalWrite(dirPin, inverted ? HIGH : LOW);
	analogWrite(spdPin, map(speed, 0, 100, 0, 255));
}


void Motor::stop() {
	analogWrite(spdPin, 0);
}

void Motor::activeStop() {
	digitalWrite(dirPin, !digitalRead(dirPin));
	analogWrite(spdPin, 3);
}
