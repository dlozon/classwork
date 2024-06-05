#ifndef IRSENSOR_H
#define IRSENSOR_H

#include <Arduino.h>

class IRSensor {
public:

    /** @brief Constructs an IRSensor object.
     *  @param dataPin The port number of the IRSensor. */
    IRSensor(int dataPin);


    /** @brief Reads the sensor.
     *  @return True if the sensor detects something. */
    bool isBlocked();
    /** @brief Reads the sensor.
     *  @return True if the sensor detects nothing. */
    bool isOpen();

private:
    int pin;
};

#endif