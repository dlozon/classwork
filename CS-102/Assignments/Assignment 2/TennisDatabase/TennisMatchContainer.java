// Dylan Lozon
// CS-102, Fall 2020
// Assignment 2

package TennisDatabase;

import java.util.*;
import java.io.*;

// Tennis match container
// Stores tennis matches in an ArrayList
class TennisMatchContainer implements TennisMatchContainerInterface 
{
   ArrayList<TennisMatch> container;
   ArrayList<TennisMatch> temp;
   
   public int getNumMatches() { return container.size(); }
   public TennisMatchContainer() { container = new ArrayList<TennisMatch>(1); }
   public void insertMatch( TennisMatch m ) { container.add(m); }
   public void reset() { container.clear(); }
   
   // Returns an array containing every match
   public TennisMatch[] getAllMatches()
   {
      TennisMatch matchArray[] = new TennisMatch[this.container.size()];
      
      for (int i = 0; i < this.container.size(); i++) 
         if ( container.get(i) != null )
            matchArray[i] = container.get(i);
      
      return matchArray;
   }  
   
   // Prints every match into the console
   public void printAllMatches()
   {
      for (int i = 0; i <= this.container.size()-1; i++) 
         if ( container.get(i) != null )
            System.out.println( container.get(i).toString());
   }
   
   // Deletes every match containing the id of a given player
   public void deleteMatchesOfPlayer( String playerId )
   {
      temp = new ArrayList<TennisMatch>(1); 
      
      for (int i = 0; i < this.container.size(); i++)
         temp.add(container.get(i));
      
      container.clear();
      
      for (int i = 0; i < temp.size(); i++) 
         if ( !temp.get(i).getIdPlayer1().equals(playerId) && !temp.get(i).getIdPlayer2().equals(playerId))
            container.add(temp.get(i));
      
      temp = null;
   }
}