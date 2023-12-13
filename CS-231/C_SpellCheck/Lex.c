// Dylan Lozon
// Project 4

// inclusions
#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <unistd.h>

// Main function declares variables,
// reads all characters in a given input file,
// and sends formatted characters to stdout.
int main( int argc, char* argv[] ) 
{
	// declarations
	FILE *inputFile;
	char prevChar;
	char currentChar;
	
	// open file for reading
	inputFile = fopen(argv[1], "r");
	
	if( inputFile == NULL )
	{
		printf("Missing or invalid input file.\n");
		exit(0); // ends here if a bad input file is given
	}
	
	while( currentChar != EOF )
	{
		currentChar = getc(inputFile);
		
		// puts the current character in stdout
		// only if it is a letter, whitespace, or newline
		if( isalpha(currentChar) != 0 )
		{
			prevChar = currentChar;
			putc(currentChar, stdout);
		}
		
		else if( currentChar == ' ' || currentChar == '\n' )
		{
			// disregards current character if there
			// are 2 whitespaces or newlines in a row
			if(!(( prevChar == ' ' || prevChar == '\n' )
			&& ( currentChar == ' ' || currentChar == '\n' )))
			{
				prevChar = currentChar;
				putc('\n', stdout);
			}
		}
		// else, disregard current character
	}
	fclose(inputFile);
}
