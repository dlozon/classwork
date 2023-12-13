#include <stdio.h>
#include <math.h>

int main() {
    printf("Hello, World!\n");
    printf("My code is compiling!\n");

    // Pythagorean Theorem

    float sideLengthA = 3.5;
    float sideLengthB = 5.75;
    float hypotenuse;

    hypotenuse = sqrt((sideLengthA * sideLengthA) + (sideLengthB * sideLengthB));

    printf("The hypotenuse of a triangle with sidelengths %f and %f units is %f units long", sideLengthA, sideLengthB, hypotenuse);
    return 0;
}