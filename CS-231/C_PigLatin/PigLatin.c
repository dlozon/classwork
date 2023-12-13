// Dylan Lozon 4/20/2021

// necessary inclusions
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int isVowel( char c ) // returns 1 if the input character is a, e, i, o, or u
{
	if ( c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' )
		return 1;
}

// takes a string as input and translates the string to pig latin, storing the string in strTranslated
void translate( char* strOriginal, char* strTranslated )
{
	if ( isVowel(strOriginal[0]) == 1 ) // case if strOriginal[0] is a vowel
	{
		// copies the input string into the output
		for( int i = 0; i < strlen(strOriginal); i++ )
			strTranslated[i] = strOriginal[i];
		
		// appends "eh" to the end of the string
		strTranslated[strlen(strTranslated)] = 'e';
		strTranslated[strlen(strTranslated)] = 'h';
	}
	
	else // case if strOriginal[0] is not a vowel
	{
		// copies the input string into the output and makes it lowercase
		for( int i = 0; i < strlen(strOriginal); i++ )
			strTranslated[i] = tolower(strOriginal[i+1]);
		
		// checks if the original word had a capital letter, and capitalizes the output accordingly
		if( strOriginal[0] >= 'A' && strOriginal[0] <='Z' )
			strTranslated[0] = toupper(strTranslated[0]);
		
		// moves the first letter of the input to the end of the output, before appending "eh"
		strTranslated[strlen(strTranslated)] = tolower(strOriginal[0]);
		strTranslated[strlen(strTranslated)] = 'e';
		strTranslated[strlen(strTranslated)] = 'h';
	}
	
	return;
}

int main( int argc, char* argv[] ) // argv[1] is the input file; argv[2] is the output file
{
	// variable declaration
	FILE *inputFile;
	FILE *outputFile;
	char currentWordIn[20];
	char currentWordOut[22];
	
	inputFile = fopen(argv[1], "r");
	outputFile = fopen(argv[2], "w+"); // uses "w+" so that if no such output file exists, the program will create one
	
	if( inputFile == NULL ) // ends here if a bad input file is given
	{
		printf("Bad input file.\n");
		exit(0);
	}
	
	while( fscanf(inputFile, "%s", currentWordIn) != EOF ) // scans each word until it reaches the end of the provided file
	{
		translate(currentWordIn, currentWordOut);
		fprintf(outputFile, "%s\n", currentWordOut );
		
		// returns the strings to null to prevent a specific error where short words 
		// would contain some letters from longer words that were translated before them
		for( int i = 0; i < 22; i++ )
		{
			currentWordIn[i] = '\0';
			currentWordOut[i] = '\0';
		}
	}
}
