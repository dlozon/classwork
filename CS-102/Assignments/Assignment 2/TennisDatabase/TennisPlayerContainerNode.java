// Dylan Lozon
// CS-102, Fall 2020
// Assignment 2

package TennisDatabase;

// Container nodes will each store a player and a list of that player's matches
// They also hold references to the nodes on the left and right of them
class TennisPlayerContainerNode implements TennisPlayerContainerNodeInterface
{
   // Declarations
   // Private
   private TennisPlayer player;
   private TennisPlayerContainerNode left;
   private TennisPlayerContainerNode right;
   private SortedLinkedList<TennisMatch> matchList = new SortedLinkedList<TennisMatch>();
   private TennisMatch matchArray[];
   private int wins = 0;
   private int losses = 0;
   
   // Getters
   public TennisPlayer getPlayer() { return this.player; }
   public TennisPlayerContainerNode getLeft() { return this.left; }
   public TennisPlayerContainerNode getRight() { return this.right; }
   public TennisMatch getMatchIndex( int i ) { return matchList.get(i); }
      
   // Setters
   public void setPlayer( TennisPlayer p ) { this.player = p; }
   public void setLeft( TennisPlayerContainerNode l ) { this.left = l; }
   public void setRight( TennisPlayerContainerNode r ) { this.right = r; }
   
   // Prints the win/loss record of the player in this node
   public void printWinLoss() { System.out.println( this.player.getId() + " has " + this.wins + " wins and " + this.losses + " losses." ); }
   
   // Inserts a match into this node
   public void insertMatch( TennisMatch m )
   {
      try { this.matchList.insert(m); }
      catch (Exception e) { /*ignore*/ }
      
      if (( this.player.getId().equals(m.getIdPlayer1()) && m.getWinner() == 1 )
      || ( this.player.getId().equals(m.getIdPlayer2()) && m.getWinner() == 2 ))
         wins++;
      else
         losses++;
   }
   
   // Returns an array of every match containing this player's id
   public TennisMatch[] getMatches()
   {
      try
      {
         matchArray = new TennisMatch[this.matchList.size()-1];
      
         for ( int i = 0; i < this.matchList.size()-1; i++ )
            matchArray[i] = matchList.get(i);
            
         return matchArray;
      }
      catch ( NegativeArraySizeException e ) { System.out.println( "This player has played no matches." ); }
      return null; // This should not be reached
   }
   
   // Removes all matches from this node
   public void deleteMatches()
   {
      for ( int i = 0; i < this.matchList.size()-1; i++ )
         this.matchList.delete(i);
   }
}