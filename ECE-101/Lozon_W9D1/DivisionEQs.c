#include <stdio.h>
#include <math.h>

/** Calculates the mean value of an array */
float calculateAverage(float numbers[], int numbersSize) {
    float sum = 0.0;
    float average;

    // Find the sum of all the numbers
    for (int i = 0; i < numbersSize; i++)
        sum += numbers[i];

    // Calculate their average
    average = sum / numbersSize;

    return average;
}

/** Normalizes an array such that the largest number = 1*/
void normalizeArray(float numbers[], int numbersSize) {
    float max = 0;
    
    // Find the largest number in the array
    for (int i = 0; i < numbersSize; i++) {
        if (numbers[i] > max) {
            max = numbers[i];
        }
    }

    // Scale each number down such that the largest number = 1
    for (int i = 0; i < numbersSize; i++) {
        numbers[i] /= max;
    }
}

int main() {
    // Declare three numbers in an array
    float numbers[3] = {2.66, 5.34, 10};
    int numbersSize = sizeof(numbers) / sizeof(numbers[0]);

    // Print the average of the numbers
    printf("The average of ");
    for (int i = 0; i < numbersSize; i++)
        printf("%f, ", numbers[i]);
    printf("is %f\n", calculateAverage(numbers, numbersSize));


    // Print the normalized array
    normalizeArray(numbers, numbersSize);

    printf("The normalized numbers are: ");
    for(int i = 0; i < numbersSize; i++)
        printf("%f, ", numbers[i]);

    return 0;
}