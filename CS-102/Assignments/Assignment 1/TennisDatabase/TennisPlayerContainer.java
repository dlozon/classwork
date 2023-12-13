// Tennis player container
// Stores tennis players 

package TennisDatabase;

import java.util.*;

class TennisPlayerContainer implements TennisPlayerContainerInterface
{
   TennisPlayerContainerNode first;
   
   public TennisPlayer getPlayer( String id )
   {
      try
      {
         TennisPlayerContainerNode temp = first; 
          
         while ( temp.player.getId().equals(id) == false && temp.getNext().player.getId().equals(first.player.getId()) == false )  
            temp = temp.getNext();
         
         if ( temp.player.getId().equals(id) == true )
            return temp.player;
         else
            return null;
      }
      // Exception occurs when list is empty
      catch (NullPointerException e) { return null; }
   }
   
   public void insertPlayer( TennisPlayer p ) 
   {
      if ( getPlayer(p.getId()) != null )
         System.out.println( "A player with this id already exists." ); // if the user gets this message the player will not be entered into the database
      else
      {
         // Check if list is empty
         if (first == null)  
         {  
            TennisPlayerContainerNode newNode = new TennisPlayerContainerNode();  
            newNode.player = p;  
            newNode.setNext(newNode);
            newNode.setPrev(newNode);  
            first = newNode;  
            return;
         }
         
         // if list is not empty, insert player
         else
         {
            TennisPlayerContainerNode last = (first).getPrev(); 
   
            // create a new node  
            TennisPlayerContainerNode newNode = new TennisPlayerContainerNode();  
            newNode.player = p;  
   
            // next of newNode will point to head since list is circular  
            newNode.setNext(first);  
   
            // previous of first will be newNode
            (first).setPrev(newNode);
   
            // change newNode => prev to last  
            newNode.setPrev(last);  
   
            // Make new node next of old last  
            last.setNext(newNode);
         }
      }
   }
   
   public void insertMatch( TennisMatch m )
   {
         TennisPlayerContainerNode current = first;
         
         TennisPlayer p1 = getPlayer(m.getIdPlayer1());
         TennisPlayer p2 = getPlayer(m.getIdPlayer2());
         
         while ( current.player.compareTo(p1) != 0 )
               current = current.getNext(); // cycles through the list until it finds player 1's id         
         current.insertMatch(m);
         
         while ( current.player.compareTo(p2) != 0 )
               current = current.getNext(); // cycles through the list until it finds player 2's id
         current.insertMatch(m);
   }
   
   public TennisPlayer[] getAllPlayers()
   {
      try
      {
         ArrayList<TennisPlayer> pArrayL = new ArrayList<>();
         TennisPlayerContainerNode current = first;
         
         // Add each player to an array to be returned
         while ( current.getNext() != null && current.getNext() != first )
         {  
            pArrayL.add( current.player );
            current = current.getNext();
         }
         // runs one extra time because the above loop always runs exactly one time too few
         pArrayL.add( current.player ); 
         
         TennisPlayer[] pArray = new TennisPlayer[pArrayL.size()];

         for ( int i = 0; i < pArrayL.size(); i++ )
            pArray[i] = pArrayL.get(i);
         
         return pArray;
      }
      catch ( NullPointerException e ) { System.out.println( "List of players is empty." ); return null; }
   }
   
   public TennisMatch[] getMatchesOfPlayer( String playerId )
   {
      // Check that player exists
      if ( getPlayer(playerId) == null )
      {
         System.out.println( "Player not found" );
         return null;
      }
      
      else
      {
         TennisPlayerContainerNode current = first;
      
         while ( current.player.compareTo(getPlayer(playerId)) != 0 )           
            current = current.getNext(); // cycles through the list until it finds player's id   
      
         return current.getMatches();
      }
   }
   
   public String getWinLoss( String playerId )
   {
      // Check that player exists
      if ( getPlayer(playerId) == null )
      {
         System.out.println( "Player not found" );
         return null;
      }
      
      else
      {
         TennisPlayerContainerNode current = first;
      
         while ( current.player.compareTo(getPlayer(playerId)) != 0 )           
            current = current.getNext(); // cycles through the list until it finds player's id   
      
         return current.winLoss();
      }
   }
   
   public void printAll()
   {
      TennisPlayerContainerNode current = first;
         
      // Display each player in the console
      while ( current.getNext() != null && current.getNext() != first )
      {  
         current.player.displayAsString();
         current = current.getNext();
      }
      // runs one extra time because the above loop always runs exactly one time too few
     current.player.displayAsString();
   }
   
   public void printMatchesOfPlayer( String playerId )
   {
      // Check that player exists
      if ( getPlayer(playerId) == null )
      {
         System.out.println( "Player not found" );
      }
      
      else
      {
         TennisPlayerContainerNode current = first;
      
         while ( current.player.compareTo(getPlayer(playerId)) != 0 )           
            current = current.getNext(); // cycles through the list until it finds player's id   
         
         current.printMatchesOfPlayer();
      }
   }
}