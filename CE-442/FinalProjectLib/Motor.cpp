#include "Arduino.h"
#include "Motor.h"


Motor::Motor(int cwPin, int ccwPin, int spdPin, bool inverted) {
	pinMode(cwPin, OUTPUT);
	pinMode(ccwPin, OUTPUT);
	pinMode(spdPin, OUTPUT);
	this->cwPin = cwPin;
	this->ccwPin = ccwPin;
	this->spdPin = spdPin;
	this->inverted = inverted; // Defaults to false
}


void Motor::setInverted(bool invert) { inverted = invert; }
bool Motor::getInverted() { return inverted; }

void Motor::setSpeed(double speed) {
	// If speed is negative, drive in reverse.
	bool dir = (speed < 0 ? false : true);

	sendPower(dir, abs(speed));
}

void Motor::forward(double speed) {	sendPower(true, speed); }
void Motor::backward(double speed) { sendPower(false, speed); }

void Motor::stop() { analogWrite(spdPin, 0); }
void Motor::activeStop() { sendPower(.03, !digitalRead(cwPin)); }

void Motor::sendPower(bool dir, double speed) {
	dir = inverted ? !dir : dir;
	speed = constrain(speed, 0, 1);

	digitalWrite(cwPin, dir);
	digitalWrite(ccwPin, !dir);

	analogWrite(spdPin, speed * 255);
}
