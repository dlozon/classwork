


// Giuseppe Turini
// CS-102, Fall 2020
// Assignment 2

package TennisDatabase;

// Interface (package-private) providing the specifications for the TennisPlayerNode class.
interface TennisPlayerContainerNodeInterface {
   
   // Accessors (getters).
   public TennisPlayer getPlayer();
   public TennisPlayerContainerNode getLeft();
   public TennisPlayerContainerNode getRight();
   
   // Modifiers (setters).
   public void setPlayer( TennisPlayer p );
   public void setLeft( TennisPlayerContainerNode lc );
   public void setRight( TennisPlayerContainerNode rc );
   
   // Desc.: Insert a TennisMatch object (reference) into this node.
   // Input: A TennisMatch object (reference).
   // Output: Throws a checked (critical) exception if match cannot be inserted in this player list.
   public void insertMatch( TennisMatch m ) throws TennisDatabaseException;
   
   // Desc.: Returns all matches of this player arranged in the output array (sorted by date, most recent first).
   // Output: Throws an unchecked (non-critical) exception if there are no matches for this player.
   public TennisMatch[] getMatches() throws TennisDatabaseRuntimeException;
   
   // Desc.: Deletes all matches of input player (id) from this node.
   // Input: The id of the tennis player.
   // Output: Throws an unchecked (non-critical) exception if no matches are deleted.
   public void deleteMatches() throws TennisDatabaseRuntimeException;
   
}


