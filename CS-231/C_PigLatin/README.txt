PigLatin was written by Dylan Lozon
for project 2 of the C portion of CS-231

------ Compiling Info ------
This file can be compiled with

gcc PigLatin.c -o PigLatin.out

------ Execution Info ------
After this has been compiled,
you will need an input file 
with text in it

To execute the program:
./Compare.out <input file> <output file>

-------- Output info --------
If you name an output file that
does not exist, it will be created.
If you name an output file that does
exist, it will be overwritten.

The output will contain the input 
text; translated to piglatin.

------ Pig Latin Info ------
If a word starts with a vowel, it 
will have "eh" appended to the end.

Ex. open --> openeh

If a word starts with a consonant, 
it will have it's first letter 
moved to the end and have "eh" 
appended to the result.

Ex. close --> loseceh

If a word is capitalized, its 
capitalization must remain consistent.

Ex. Close -x-> loseCeh
Ex. Close --> Loseceh
