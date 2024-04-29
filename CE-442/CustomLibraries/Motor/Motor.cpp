#include "Arduino.h"
#include "Motor.h"


Motor::Motor(int d_pin, int s_pin) {
  pinMode(d_pin, OUTPUT);
  pinMode(s_pin, OUTPUT);
  dir_pin = d_pin;
  speed_pin = s_pin;
  inverted = false;
}

void Motor::setInverted(bool invert) { inverted = invert; }
bool Motor::getInverted() { return inverted; }


void Motor::setSpeed(int speed) {
	// If speed is negative, drive in reverse.
	bool dir = (speed <= 0 ? LOW : HIGH);
	// If inverted, invert the direction.
	dir = inverted ? !dir : dir;

	// Drive the motor
	digitalWrite(dir_pin, dir);
	analogWrite(speed_pin, map(speed, 0, 100, 0, 255));
}

void Motor::forward(int speed) {
	digitalWrite(dir_pin, inverted ? LOW : HIGH);
	analogWrite(speed_pin, map(speed, 0, 100, 0, 255));
}

void Motor::backward(int speed) {
	digitalWrite(dir_pin, inverted ? HIGH : LOW);
	analogWrite(speed_pin, map(speed, 0, 100, 0, 255));
}


void Motor::stop() {
	analogWrite(speed_pin, 0);
}

void Motor::activeStop() {
	digitalWrite(dir_pin, !digitalRead(dir_pin));
	analogWrite(speed_pin, 3);
}
