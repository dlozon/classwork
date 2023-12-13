// Dylan Lozon
// Project 5

// inclusions
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <errno.h>
#include <unistd.h>
#include <sys/wait.h>

// Uses a loop to call each of the commands
// and to manipulate the pipes. Runs
// the following command in the console:

// ./lex.out argv[1] | sort -f | uniq -i | 
// ./compare.out argv[2] argv[3]

// In summary, main checks the spelling
// of words in argv[1] against the dictionary
// argv[2] and writes the result to argv[3]
int main( int argc, char* argv[] )  
{	
	// these are the 4 commands being run
	char* commands[][4] = 
	{
		// each command must be NULL terminated
		{"./lex.out", argv[1], NULL},
		{"sort", "-f", NULL},
		{"uniq", "-i", NULL},
		{"./compare.out", argv[2], argv[3], NULL}
	};
	
	// declarations
	int prevPipe;
	int p[2];
	int i = 0;
	
	// pipes together the first 3 commands
	for( i = 0; i < 3; i++ ) 
	{
		pipe(p);
		if( fork() == 0 ) 
		{
			// redirect previous pipe to stdin
			if( prevPipe != 0 ) 
			{
				dup2(prevPipe, 0);
				close(prevPipe);
			}
			
			// redirect stdout to current pipe
			dup2(p[1], 1);
			close(p[1]);
			
			// run the current command
			execvp(commands[i][0], commands[i]);
			perror("execvp failed (line 58)");
			// close program if current command is not run
			exit(1); 
		}
		
		// close pipes
		close(prevPipe);
		close(p[1]);
		prevPipe = p[0];
	}
	
	// get stdin from the final pipe
	if( prevPipe != 0 ) 
	{
        	dup2(prevPipe, 0);
        	close(prevPipe);
    	}
	
	// wait for children
	for( i = 0; i < 3; i++ )
		wait(NULL);
		
    	// run last command
    	execvp(commands[i][0], commands[i]);
    	perror("execvp failed (line 82)");
    	
    	return(1);
}
