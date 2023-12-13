


// Giuseppe Turini
// CS-102, Fall 2020
// Assignment 1

package TennisDatabase;

// Interface (package-private) providing the specifications for the TennisMatchesContainer class.
interface TennisMatchContainerInterface {

   // Desc.: Insert a tennis match into this container.
   // Input: A tennis match.
   // Output: Throws a checked (critical) exception if the container is full.
   public void insertMatch( TennisMatch m ) throws TennisDatabaseException;
   
   // Desc.: Returns all matches in the database arranged in the output array (sorted by date, most recent first).
   // Output: Throws an exception if there are no matches in this container.
   public TennisMatch[] getAllMatches() throws TennisDatabaseRuntimeException;
   
   // getMatchesOfPlayer has already been implemented in the player container
}


