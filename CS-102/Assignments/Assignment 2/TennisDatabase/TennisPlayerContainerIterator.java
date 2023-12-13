// Dylan Lozon
// CS-102, Fall 2020
// Assignment 2

package TennisDatabase;

import java.util.*;

// Iterator made for TennisPlayerContainer that uses a circular array queue
class TennisPlayerContainerIterator implements Iterator
{
   // Declarations
   private int currentIndex;
   private TennisPlayer currentPlayer;
   private TennisPlayerQueue queue;
   
   // Constructor
   public TennisPlayerContainerIterator( TennisPlayerQueue q )
   {
      currentIndex = 0;
      this.queue = q;
   }
   
   // Returns true if the current index is not the index of the last player
   public boolean hasNext()
   {
      if ( queue.isEmpty() == false && currentIndex+1 <= queue.getEnd() )
         return true;
      return false;
   }
   
   // Returns the next player in line
   public TennisPlayer next()
   {
      if ( currentPlayer == null )
      {
         currentIndex++;
         currentPlayer = queue.get( currentIndex );
         next();
      }
      else if ( currentIndex == queue.size())
      {
         currentPlayer = queue.get(0);
         currentIndex = 0;
      }
      else
      {
         currentPlayer = queue.get( currentIndex + 1 );
         currentIndex++;
      }
      return currentPlayer;
   }
   
   // Removes the current player
   public void remove()
   {
      currentPlayer = null;
   }
}