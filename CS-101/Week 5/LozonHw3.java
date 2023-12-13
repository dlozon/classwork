//Dylan Lozon Homework 3
//Make a game of Nim

import java.util.Random;
import java.util.Scanner;

public class LozonHw3
{
   
   //This is where the CPU's turn happens
   static void CpuTurn ( int toothpicks )
   {
      Random rand = new Random( );
      
      int remove = 0;
      
      if ( toothpicks == 4 )
      {
         remove = 3;
         System.out.println( "There are " + toothpicks + " left and I will remove " + remove + " of them." );
         toothpicks = toothpicks - remove;
      }        
      else if ( toothpicks == 3 )
      {
         remove = 2;
         System.out.println( "There are " + toothpicks + " left and I will remove " + remove + " of them." );
         toothpicks = toothpicks - remove;
      } 
      else if ( toothpicks == 2 )
      {
         remove = 1;
         System.out.println( "There are " + toothpicks + " left and I will remove " + remove + " of them." );
         toothpicks = toothpicks - remove;
      }
      else
      {
         remove = 1 + rand.nextInt(3);
         System.out.println( "There are " + toothpicks + " left and I will remove " + remove + " of them." );
         toothpicks = toothpicks - remove;
      }
      
      if ( toothpicks == 1 )
      {
         System.out.println( "Looks like I win this time, gg" );
         StartGameDialogue( true );
      }
      
      PlayerTurn( false, false, toothpicks );
   }

   //This is where the player's turn happens, This is also where all code for the first turn is executed
   static void PlayerTurn ( boolean firstTurn, boolean playerTurn1, int toothpicks )
   {
      Scanner scan =  new Scanner( System.in );
      Random rand = new Random( );
      
      int remove = 0;
      
      //This acts differently depending on whether it's the first turn and whether the player took the first turn
      if ( playerTurn1 == true )
      {   
         System.out.println( "Pick a number of toothpicks from 6-28." );
         toothpicks = scan.nextInt( );
         
         if (!( toothpicks >= 6 && toothpicks <= 28 ))
         {
            System.out.println( "That is not a correct number, I will pick for you" );
            toothpicks = 6 + rand.nextInt( 23 );
            firstTurn = false;
         }
         else
         {
            firstTurn = false;
         }
      }
      else if ( playerTurn1 == false && firstTurn == true )  
      {
         toothpicks = 6 + rand.nextInt(23);
         System.out.println( "There are " + toothpicks + " toothpicks remaining, how many will you remove? (1-3)" );
         remove = scan.nextInt( );
         if (!( remove >= 1 && remove <= 3 ))
         {  
            while (!( remove >= 1 && remove <= 3 ))
            {
               System.out.println( "That is not a correct number, pick a number 1-3" );
               remove = scan.nextInt( );
            }   
         }
         else
         {
            toothpicks = toothpicks - remove;
            System.out.println( "You remove " + remove + " toothpicks, leaving " + toothpicks + " toothpicks." );
         }
      }
      //This is what all player turns do after the first one
      else
      {
         System.out.println( "There are " + toothpicks + " toothpicks remaining, how many will you remove? (1-3)" );
         remove = scan.nextInt( );
         if (!( remove >= 1 && remove <= 3 ))
         {  
            while (!( remove >= 1 && remove <= 3 ))
            {
               System.out.println( "That is not a correct number, pick a number 1-3" );
               remove = scan.nextInt( );
            }   
         }
         else
         {
            toothpicks = toothpicks - remove;
            System.out.println( "You remove " + remove + " toothpicks, leaving " + toothpicks + " toothpicks." );
         }
      }
      
      if ( toothpicks == 1 )
      {
         System.out.println( "Looks like you beat me, congratulations!" );
         StartGameDialogue( true );
      }

      CpuTurn( toothpicks );
   } 
   
   //This code runs y/n events and returns a true or false value depending on the response
   static boolean YesOrNo( )
   {
      Scanner scan =  new Scanner( System.in );
      
      String response = scan.next( );
      
      char yn = response.charAt( 0 );    
         
      if ( yn == 'y' )
         return true;
      else if ( yn == 'n' )
         return false;
      else
      {
         System.out.println( "'y' or 'n' only please." );
         YesOrNo();
      }
      
      return true;
   }
   
   //This code runs when you start the game and pays attention to whether or not it's your first game   
   static void StartGameDialogue( boolean restart )
   {
      if ( restart == true )
      {
         System.out.println( "Do you want to go again?\ny/n" );
         
         if ( YesOrNo( ) == true )
            StartGameDialogue( false );
         else 
         {
            System.out.print( "Thanks for playing, I hope to see you again!" );
            System.exit( 0 );
         }
      }   
      else
      {
         System.out.println( "Would you like to hear the rules of Nim?\ny/n" );
         
         if ( YesOrNo( ) == true )
         {
            System.out.println( "There are somewhere between 6 and 28 toothpicks, one of us will pick up 1-3 of them and then the other player will do the same." );
            System.out.println( "The player who has to pick up the last stick is the loser." );
         }
         else
            System.out.println( "You seem ready, let's jump right into the game!" );
      }
      
      //This code initiates the game
      System.out.println( "Would you like to go first?\ny/n" );
      if ( YesOrNo( ) == true )
         PlayerTurn( true,true, 0 );
      else
      {
         PlayerTurn( true,false, 0 );
      }
   }  
     
   public static void main( String [] args )
   {
      StartGameDialogue( false );
   }
}