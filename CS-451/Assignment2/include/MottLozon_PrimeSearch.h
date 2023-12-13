#ifndef PRIMESEARCH_H
#define PRIMESEARCH_H


/** Authors: Samantha Mott and Dylan Lozon
 *  Although C does not technically have built in class support,
 *  it just makes sense for PrimeSearch to be it's own class,
 *  so I decided to try implementing it using object oriented paradigms.
*/


// This struct holds the PrimeSearch object's properties
struct PrimeSearch {
    unsigned long current_highest_search;
    unsigned long current_highest_prime;
};

/*
Function Name: PrimeSearch_create
Input to the method: starting value to search
Output(Return value): a pointer to new instance of a PrimeSearch struct
Description: This is the object constructor.
*/
struct PrimeSearch* PrimeSearch_create();

/*
Function Name: getHighestSearch
Input to the method: self
Output(Return value): the highest search performed so far
Description: This is a standard getter method.
*/
unsigned long getHighestSearch(struct PrimeSearch* obj);

/*
Function Name: getHighestPrime
Input to the method: self
Output(Return value): the highest prime number found so far
Description: This is a standard getter method.
*/
unsigned long getHighestPrime(struct PrimeSearch* obj);

/*
Function Name: getHighestPrime
Input to the method: self
Output(Return value): the highest prime number found so far
Description: This is a standard getter method.
*/
void checkNext(struct PrimeSearch* obj);

/*
Function Name: getHighestPrime
Input to the method: self
Output(Return value): N/A
Description: This method destroys the object by freeing it from memory
*/
void PrimeSearch_destroy(struct PrimeSearch* obj);

/*
Function Name: signalHandler
Input to the method: the number of the signal being recieved
Output(Return value): N/A
Description: This defines the program's behavior when recieving a signal
*/
void signalHandler(int signum);

#endif
