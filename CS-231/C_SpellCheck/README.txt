SpellCheck was written by Dylan Lozon
for project 5 of the C portion of CS-231

------ Compiling Info ------
Each file can be compiled with

gcc Compare.c -o Compare.out
gcc Lex.c -o Lex.out
gcc SpellCheck.c -o SpellCheck.out

------ Execution Info ------
After all files have been compiled,
you will need:
1) An input file with text in it
2) A dictionary file (provided)

To execute the program:
./Compare.out <input file> <dictionary file> <output file>

-------- Output info --------
If you name an output file that
does not exist, it will be created.
If you name an output file that does
exist, it will be overwritten.

The output will contain an 
alphabetically organized list
of all words in the input file,
as well an indication of whether
each word was matched in the
dictionary or not.
