// Container nodes will each store a player and a list of that player's matches

package TennisDatabase;

class TennisPlayerContainerNode implements TennisPlayerContainerNodeInterface
{
   TennisPlayer player;
   TennisPlayerContainerNode prev;
   TennisPlayerContainerNode next;
   SortedLinkedList<TennisMatch> mList = new SortedLinkedList<>();
   
   // Accessors (getters).
   public TennisPlayer getPlayer() { return player; }
   public String getPlayerId() { return player.getId(); }
   public TennisPlayerContainerNode getPrev() { return this.prev; }
   public TennisPlayerContainerNode getNext() { return this.next; }
   
   // Modifiers (setters).
   public void setPrev( TennisPlayerContainerNode p ) { this.prev = p; }
   public void setNext( TennisPlayerContainerNode n ) { this.next = n; }
   
   public void insertMatch( TennisMatch m )
   {
      // Catches Exception thrown by SortedLinkedList's insert method
      try
      {
         mList.insert(m);
      }
      catch( Exception e )
      {
         System.out.println("Insertion was unsuccessful.");
      }
   }
   
   public TennisMatch[] getMatches()
   {
      TennisMatch[] mArray = new TennisMatch[mList.size()];
      
      for (int i = 0; i < mList.size(); i++)
         mArray[i] = mList.get(i);
         
      return mArray;
   }
   
   public String winLoss()
   {
      int pNum;
      int winCount = 0;
      int lossCount = 0;
      
      // Check if player is player 1 or player 2 for each match
      for (int i = 0; i < mList.size(); i++)
      {
         if ( mList.get(i).getIdPlayer1().equals(player.getId()) == true )
            pNum = 1;
         else
            pNum = 2;
         
         // Count wins and losses
         if ( mList.get(i).getWinner() == pNum )
            winCount++;
         else
            lossCount++;
      }
      
      return winCount + "/" + lossCount;
   }
   
   public void printMatchesOfPlayer()
   {
      for (int i = 0; i < mList.size(); i++)
         mList.get(i).displayAsString();
   }
}