


// Giuseppe Turini
// CS-102, Fall 2020
// Assignment 2

package TennisDatabase;

// Interface (package-private) providing the specifications for the TennisMatchesContainer class.
interface TennisMatchContainerInterface {

   // Desc.: Returns the number of matches in this container.
   // Output: The number of matches in this container as an integer.
   public int getNumMatches();

   // Desc.: Insert a tennis match into this container.
   // Input: A tennis match.
   // Output: Throws a checked (critical) exception if the container is full.
   public void insertMatch( TennisMatch m ) throws TennisDatabaseException;
   
   // Desc.: Returns all matches in the database arranged in the output array (sorted by date, most recent first).
   // Output: Throws an exception if there are no matches in this container.
   public TennisMatch[] getAllMatches() throws TennisDatabaseRuntimeException;
   
   // getMatchesOfPlayer was moved to TennisPlayerContainer 
      
   // Desc.: Delete all matches of a player by id (if found).
   // Output: Throws an unchecked (non-critical) exception if there is no match with that input id.
   public void deleteMatchesOfPlayer( String playerId ) throws TennisDatabaseRuntimeException;
   
}


