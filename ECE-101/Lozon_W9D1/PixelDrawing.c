#include <stdio.h>

// Function to display the first image
void displayImage1() {
    int image1[5][5] = {
        {255, 255, 255, 255, 255},
        {255,  0,   0,   0,  255},
        {255,  0,   0,   0,  255},
        {255,  0,   0,   0,  255},
        {255, 255, 255, 255, 255}
    };

    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            printf("%3d ", image1[i][j]);
        }
        printf("\n");
    }
}

/** Display the second image */
void displayImage2() {
    int image2[5][5] = {
        {255, 0,  0,  0, 255},
        {0,  255, 0, 255,  0},
        {0,   0, 255,  0,  0},
        {0,  255, 0, 255,  0},
        {255, 0,  0,  0, 255},
    };

    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            printf("%3d ", image2[i][j]);
        }
        printf("\n");
    }
}

int main() {
    int input = 0;

    while (input != 2) {
        printf("Enter a number (0, 1) or 2 to quit: ");
        scanf("%d", &input);

        switch (input) {
            case 0:
                displayImage1();
                break;
            case 1:
                displayImage2();
                break;
            case 2:
                printf("Exiting the program...\n");
                break;
            default:
                printf("Invalid input. Please try again.\n");
                break;
        }
    }

    return 0;
}
