// Dylan Lozon
// Project 4

// inclusions
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Main function declares variables,
// reads strings from stdin,
// checks the strings against a provided dictionary,
// and tells which words exist in an output file.
int main( int argc, char* argv[] ) 
{
	// Variable declaration
	FILE *dictionary;
	FILE *outputFile;
	char* emptyString;
	char currentInput[32];
	char currentDict[32];
	size_t len;
	
	// opens files for reading and writing
	dictionary = fopen(argv[1], "r");
	outputFile = fopen(argv[2], "w+");
	
	// moves current dict to the first word
	fgets(currentDict, 32, dictionary);
	
	while(1) 
	{	
		// takes a string from stdin
		fgets(currentInput, 32, stdin);
		
		// ends the loop if end of stdin is reached
		if( feof(stdin))
			break;
		
		// scans through the provided dictionary until
		// the current word or a word after it is found
		while( strcasecmp(currentDict, currentInput) < 0 )
			fgets(currentDict, 32, dictionary);
		
		// removes newline characters from both words
		currentInput[strlen(currentInput)-1] = '\0'; 
		currentDict[strlen(currentDict)-1] = '\0';
		
		// outputs whether the word found is
		// the word we were looking for or not
		// (whether or not it's in the dictionary)
		if( strcasecmp(currentDict, currentInput) == 0 )
			fprintf(outputFile, "\"%s\" is a word.\n", currentInput);
		
		else // whitespace makes non-words more noticeable
			fprintf(outputFile, 
			"			\"%s\" is not a word.\n", currentInput);
	}
	fclose(dictionary);
	fclose(outputFile);
}
