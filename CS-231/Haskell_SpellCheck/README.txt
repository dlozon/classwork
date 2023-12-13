SpellCheck was written by Dylan Lozon
for project 2 of the Haskell portion of CS-231

------ Compiling Info ------
This project can be compiled with:
ghc SpellCheck.hs -o SpellCheck.out

------ Execution Info ------
After the file has been compiled,
you will need:
1) An input file with text in it
2) A dictionary file (provided)

To execute the program:
./SpellCheck.out <input file> <dictionary file> <output file>

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
