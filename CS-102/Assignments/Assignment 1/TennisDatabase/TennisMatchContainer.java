// Tennis player container
// Stores tennis players 
package TennisDatabase;

import java.util.*;

public class TennisMatchContainer implements TennisMatchContainerInterface 
{
   TennisMatch container[];
   int count;
   int size;

   
   public TennisMatchContainer()
   {
      this.container = new TennisMatch[1];
      this.count = 0;
      this.size = 1;
   }
   
   public void insertMatch( TennisMatch m )
   {
      if ( this.count == this.size )
         doubleSize();
      
      container[this.count] = m;
      count++;
   }
   
   public void doubleSize()
   {
      // Make a temporary array that is doubly as large as the container
      TennisMatch temp[] = null; 
      temp = new TennisMatch[this.size * 2]; 
      
      for (int i = 0; i < this.size; i++) 
         temp[i] = container[i]; // Copy the container into temporary array
  
      container = temp; // Container now has the same contents as temp
         
      this.size = this.size * 2;
   }
   
   public TennisMatch[] getAllMatches()
   {
      return container;
   }  
   
   public void printAllMatches()
   {
      for (int i = 0; i < this.size; i++) 
         if ( container[i] != null )
            container[i].displayAsString();
   }
}