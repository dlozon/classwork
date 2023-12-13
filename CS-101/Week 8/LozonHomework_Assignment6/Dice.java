//Dylan Lozon Homework 6
//Create a game of pig

import java.util.Scanner; 
import java.util.Random;

public class Dice
{
   private static int turnScore;
   private static int playerScore;
   private static int aiScore;
   
   private int die1Value;
   private int die2Value;
   
   public Dice( )
   {
      this.die1Value = 0;
      this.die2Value = 0;
   }
   
   public void roll( )
   {
      Random rand = new Random();
      
      this.die1Value = rand.nextInt(6) + 1;
      this.die2Value = rand.nextInt(6) + 1;
   }
   
   public void boxCars( )
   {
      int boxCars = 0;
      for ( int i = 1; i <= 1000; i++ )
      {
         roll();
         
         if ( getDie1() == 6 && getDie2() == 6 )
            boxCars++;
      }
      
      System.out.println( boxCars + " boxcars were rolled out of 1000 attempts." );
      
   }
   
   public int getDie1( )
   {
      return this.die1Value;
   }
   
   public int getDie2( )
   {
      return this.die2Value;
   }
      
   public int getDiceTotal( )
   {
      return this.die1Value + this.die2Value;
   }
   
   static boolean yesOrNo( )
   {
      Scanner scan =  new Scanner( System.in );
      
      String response = scan.next( );
      char yn = response.charAt( 0 );    
         
      if ( yn == 'y' || yn == 'Y' )
         return true;
         
      else if ( yn == 'n' || yn == 'N' )
         return false;
         
      else
      {
         System.out.println( "'y' or 'n' only please." );
         yesOrNo();
      }
      
      return true;
   }
   
   static void playerTurn( )
   {
      Dice playerDice = new Dice();
      
      playerDice.roll();
      int score = playerDice.getDiceTotal();
      
      if ( playerDice.getDie1() == 1 && playerDice.getDie2() == 1 )
      {
         turnScore = -999;
         updatePlayerScore(turnScore);
         
         System.out.println( "You rolled a " + playerDice.getDie1() + " and a " + playerDice.getDie2() + " all of your points are forfeit.\n" +
         "Your score is now " + playerScore + ".\n" );
         turnScore = 0;
         aiTurn();
      }  
      
      else if ( playerDice.getDie1() == 1 || playerDice.getDie2() == 1 )
      {
         turnScore = 0;
         updatePlayerScore(turnScore);
         
         System.out.println( "You rolled a " + playerDice.getDie1() + " and a " + playerDice.getDie2() + " your points from this turn are forfeit.\n" +
         "Your score is now " + playerScore + ".\n" );
         aiTurn();
      }
      
      else
      {  
         turnScore += score;
         System.out.print( "You rolled a " + playerDice.getDie1() + " and a " + playerDice.getDie2() + ", resulting in " + playerDice.getDiceTotal() + ".\n" +
         "Your score is now " + ( playerScore + turnScore ) + ".");
         
         if ( playerScore + turnScore >= 100 )
         {
            System.out.print( "Congrats! You beat the AI!" );
            System.exit(0);
         }
         
         System.out.println( " Would you like to roll again?" );
         
         if ( yesOrNo() == true )
            playerTurn();
         else
         {
            updatePlayerScore(turnScore);
            System.out.println( "You added " + turnScore + " points this turn, bringing you to a total of " + playerScore + " points.\n" );
            
            turnScore = 0;
            aiTurn();
         }
      }
   }
   
   static void aiTurn( )
   {
      Dice aiDice = new Dice();
      Random rand = new Random();
      
      aiDice.roll();
      int score = aiDice.getDiceTotal();
      
      if ( aiDice.getDie1() == 1 && aiDice.getDie2() == 1 )
      {
         turnScore = -999;
         updateAiScore(turnScore);
         
         System.out.println( "CPU rolled a " + aiDice.getDie1() + " and a " + aiDice.getDie2() + " all of CPU's points are forfeit.\n" +
         "CPU's score is now " + aiScore + ".\n" );
         turnScore = 0;
         playerTurn();
      }  
      
      else if ( aiDice.getDie1() == 1 || aiDice.getDie2() == 1 )
      {
         turnScore = 0;
         updateAiScore(turnScore);
         
         System.out.println( "CPU rolled a " + aiDice.getDie1() + " and a " + aiDice.getDie2() + " CPU's points from this turn are forfeit.\n" +
         "CPU's score is now " + aiScore + ".\n" );
         playerTurn();
      }
      
      else
      {
         turnScore += score;
         System.out.println( "CPU rolled a " + aiDice.getDie1() + " and a " + aiDice.getDie2() + ", resulting in " + aiDice.getDiceTotal() + ".\n" +
         "CPU's score is now " + ( aiScore + turnScore ));
         
         if ( aiScore + turnScore >= 100 )
         {
            System.out.print( "Aww, the AI wins, better luck next time!" );
            System.exit(0);
         }
         
         int aggro = playerScore - ( aiScore + turnScore ); 
         int randomNum = rand.nextInt(201) - 100;
         
         if ( turnScore >= 20 )
            aggro = -100;
            
         if ( aggro <= randomNum )
         {
            System.out.println( "CPU will roll again" );
            aiTurn();
         }
         
         else 
         {
            System.out.println( "The CPU is handing over the dice." );
            
            updateAiScore(turnScore);
            System.out.println( "CPU added " + turnScore + " points this turn, bringing them to a total of " + aiScore + " points.\n" );
            
            turnScore = 0;
            playerTurn();
         }
      }
   }
   
   static void updatePlayerScore( int score )
   {
      playerScore += score;
      
      if ( playerScore < 0 )
         playerScore = 0;
   }
   
   static void updateAiScore( int score )
   {
      aiScore += score;
      
      if ( aiScore < 0 )
         aiScore = 0;
   }
   
   public static void main( String [] args ) 
   {
      Dice dice = new Dice();
      
      System.out.println( "Let's play pig, would you like to hear the rules of pig? y/n" );  
      if ( yesOrNo() == true )
         System.out.println( "One player will roll a set of dice and add the total to their score, they are then given the option to roll again.\n" +
         "If at any time they roll a 1, they lose points accumulated that turn.\n" + 
         "If they roll snake eyes, they lose all points accumulated thus far, first player to 100 wins.\n" );
      
      System.out.println( "Would you like to go first? y/n" );  
      if ( yesOrNo() == true )
         playerTurn();
      else
         aiTurn();
   }
}