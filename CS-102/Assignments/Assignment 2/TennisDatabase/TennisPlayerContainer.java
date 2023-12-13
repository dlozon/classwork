// Dylan Lozon
// CS-102, Fall 2020
// Assignment 2

package TennisDatabase;

import java.util.*;
import java.io.*;

// Tennis player container
// Stores tennis players 
class TennisPlayerContainer implements TennisPlayerContainerInterface
{
   private TennisPlayerContainerNode root = null;
   private ArrayList<TennisPlayer> allPlayers = new ArrayList<TennisPlayer>(1);
   
   // Calls the search method with only an id
   public TennisPlayerContainerNode search( String id ) { return search( root, id ); }
   
   // Returns the node that contains the player being searched
   private TennisPlayerContainerNode search( TennisPlayerContainerNode node, String id )
   {
      try
      {
         // Base case
         if ( root == null || node.getPlayer().getId().equals(id) )
            return node;
         
         else if ( node.getPlayer().getId().compareTo(id) < 0 )
            return search(node.getRight(), id);
            
         else
            return search(node.getLeft(), id);
      }
      catch ( NullPointerException e ) { return null; }
   }
   
   // Returns the player being searched
   public TennisPlayer getPlayer( String id ) 
   {
      try
      {
         TennisPlayerContainerNode node = search(id);
         return node.getPlayer();
      }
      catch ( NullPointerException e ) { return null; }
   }
   
   // Inserts a player into the binary search tree
   public void insertPlayer( TennisPlayer p ) 
   {
         TennisPlayerContainerNode newNode = new TennisPlayerContainerNode();
         newNode.setPlayer(p);
         
      if ( search(p.getId()) != null )
         System.out.println( "A player with this id already exists." ); // if the user gets this message the player will not be entered into the database
         
      // Sets new player as the root if tree is empty
      else if ( root == null )
      {
         allPlayers.add(p);
         root = newNode;
         return;
      }
      
      else
      {
         allPlayers.add(p);
         TennisPlayerContainerNode current = root;
         TennisPlayerContainerNode parent = null;
         
         while ( true )
         {
            parent = current;
            
            // Goes left if player's id comes alphabetically before the player it's being compared to 
            if ( p.compareTo(current.getPlayer()) == 1 )
            {
               current = current.getLeft();
               
               if ( current == null )
               {
                  parent.setLeft(newNode);
                  return;
               }
            }
            
            // Goes right if player's id comes alphabetically after the player it's being compared to
            else
            {
               current = current.getRight();
               
               if ( current == null )
               {
                  parent.setRight(newNode);
                  return;
               }
            }
         }
      }
   }
   
   // Calls the deletePlayer method with only an id
   public void deletePlayer( String id ) { deletePlayer( root, getPlayer(id)); }
   
   // Removes a node with a given player and recconects the tree if need be
   private TennisPlayerContainerNode deletePlayer( TennisPlayerContainerNode node, TennisPlayer p )
   {
      try
      {
         if ( node == null )
         {
            return null;
         }
         
         else
         {
            if ( p.compareTo(node.getPlayer()) == 1 )
               node.setLeft(deletePlayer(node.getLeft(), p));
               
            else if ( p.compareTo(node.getPlayer()) == -1 )
               node.setRight(deletePlayer(node.getRight(), p));
               
            else
            {
               if ( node.getLeft() == null && node.getRight() == null )
                  node = null;
   
               else if ( node.getLeft() == null )
                  node = node.getRight();
                     
               else if ( node.getRight() == null )
                  node = node.getLeft();
                     
               else 
               {
                  TennisPlayerContainerNode temp = smallest(node.getRight());
                  node.setPlayer(temp.getPlayer());
                  node.setRight(deletePlayer(node.getRight(), temp.getPlayer()));
               }
            }
            allPlayers.remove(p);
            return node;
         }
      }
      catch ( NullPointerException e ) { System.out.println( "Player not found." ); }
      return null; // Should never be reached
   }
   
   // A support method for deletePlayer
   private TennisPlayerContainerNode smallest( TennisPlayerContainerNode node ) 
   {  
      if ( node.getLeft() != null )  
         return smallest( node.getLeft() );  
      else  
         return node;  
   }
   
   // Inserts a match into the nodes of its players
   public void insertMatch( TennisMatch m )
   {
      if ( getPlayer(m.getIdPlayer1()) == null || getPlayer(m.getIdPlayer2()) == null )
         System.out.println( "One or more players in this match do not exist" );
      
      else
         search(m.getIdPlayer1()).insertMatch(m);
         search(m.getIdPlayer2()).insertMatch(m);
   }
   
   // Returns an array of all players in postorder
   public TennisPlayer[] getAllPlayers()
   {
      TennisPlayer[] allPlayersArray = new TennisPlayer[allPlayers.size()];
      
      for ( int i = 0; i < allPlayers.size(); i++ )
         allPlayersArray[i] = allPlayers.get(i);
      
      return allPlayersArray;
   }
   
   // Returns the matches stored in a given player's node
   public TennisMatch[] getMatchesOfPlayer( String id ) { return search(id).getMatches(); }
   
   // Returns the win/loss rate of a given player
   public void getWinLoss( String id )
   {
      search(id).printWinLoss();
   }
   
   // Prints all players to the console
   public void printAll()
   {
      for ( int i = 0; i < allPlayers.size(); i++ )
      {
         System.out.print( allPlayers.get(i).toString());
         search(allPlayers.get(i).getId()).printWinLoss();
      }
   }
   
   // Prints all matches containing a given id
   public void printMatchesOfPlayer( String id )
   {
      try
      {
         for ( int i = 0; i <= search(id).getMatches().length; i++ )
            System.out.print( search(id).getMatchIndex(i).toString());
      }
      catch ( NullPointerException e ) { System.out.println( "Player not found" ); }
   }
   
   // Deletes all matches from the node of a given player
   public void deleteMatchesOfPlayer( String id )
   {
      search(id).deleteMatches();
   }
   
   // Calls getNumPlayers
   public int getNumPlayers() { return getNumPlayers(root); }
   
   // Returns the number of players currently stored
   public int getNumPlayers( TennisPlayerContainerNode node )
   {
      if ( node !=  null )
      {
			getNumPlayers( node.getLeft() );
			getNumPlayers( node.getRight() );
         return 1;
		}
      return 0;
   }
   
   // Prints a given player to the console
   public void printPlayer( String id )
   {
      try { System.out.print( search(id).getPlayer().toString()); }
      catch ( NullPointerException e ) { System.out.println( "Player not found" ); }
   }
   
   // Removes all players
   public void reset()
   {
      for ( int i = 0; i <= allPlayers.size()-1; i++ )
         deletePlayer(allPlayers.get(i).getId());
      
      allPlayers.clear();
      root = null;
   }
   
   // Creates a TennisPlayerContainerIterator object
   // The iterator uses a TennisPlayerQueue object that stores all players in inorder
   public TennisPlayerContainerIterator inorderIterator()
   {
      TennisPlayerContainerIterator iterator = new TennisPlayerContainerIterator( inorder());
      return iterator;
   }
   
   // Creates a TennisPlayerContainerIterator object
   // The iterator uses a TennisPlayerQueue object that stores all players in reverseInorder
   public TennisPlayerContainerIterator reverseInorderIterator()
   {
      TennisPlayerContainerIterator iterator = new TennisPlayerContainerIterator( reverseInorder());
      return iterator;
   }
   
   // Creates a TennisPlayerContainerIterator object
   // The iterator uses a TennisPlayerQueue object that stores all players in preorder
   public TennisPlayerContainerIterator preorderIterator()
   {
      TennisPlayerContainerIterator iterator = new TennisPlayerContainerIterator( preorder());
      return iterator;
   }
   
   // Creates a TennisPlayerContainerIterator object
   // The iterator uses a TennisPlayerQueue object that stores all players in postorder
   public TennisPlayerContainerIterator postorderIterator()
   {
      TennisPlayerContainerIterator iterator = new TennisPlayerContainerIterator( postorder());
      return iterator;
   }
   
   // All methods below are used to create queues in the chosen modality
   // These queues are used only for the above iterators
   public TennisPlayerQueue inorder() 
   { 
      TennisPlayerQueue q = new TennisPlayerQueue();
      inorder(root, q); 
      return q;
   }
   private TennisPlayerQueue inorder( TennisPlayerContainerNode node, TennisPlayerQueue q )
   {
      if ( node == null ) 
         return null; 
       
      inorder(node.getLeft(), q); 
      q.enqueue(node.getPlayer()); 
      inorder(node.getRight(), q);
      return q;
   }
   
   public TennisPlayerQueue reverseInorder() 
   { 
      TennisPlayerQueue q = new TennisPlayerQueue();
      inorder(root, q);
      return q;
   }
   private TennisPlayerQueue reverseInorder( TennisPlayerContainerNode node, TennisPlayerQueue q )
   {
      if ( node == null ) 
         return null; 
       
      reverseInorder(node.getRight(), q);
      q.enqueue(node.getPlayer());
      reverseInorder(node.getLeft(), q);
      return q;
   }
   
   public TennisPlayerQueue preorder() 
   { 
      TennisPlayerQueue q = new TennisPlayerQueue();
      inorder(root, q);
      return q;
   }
   private TennisPlayerQueue preorder( TennisPlayerContainerNode node, TennisPlayerQueue q )
   {
      if ( node == null ) 
         return null; 
       
      q.enqueue(node.getPlayer());
      reverseInorder(node.getLeft(), q);
      reverseInorder(node.getRight(), q);
      return q;
   }
   
   public TennisPlayerQueue postorder() 
   { 
      TennisPlayerQueue q = new TennisPlayerQueue();
      inorder(root, q);
      return q;
   }
   private TennisPlayerQueue postorder( TennisPlayerContainerNode node, TennisPlayerQueue q )
   {
      if ( node == null ) 
         return null; 
       
      reverseInorder(node.getLeft(), q);
      reverseInorder(node.getRight(), q);
      q.enqueue(node.getPlayer());
      return q;
   }
}