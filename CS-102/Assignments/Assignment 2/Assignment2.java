// Dylan Lozon
// CS-102, Fall 2020
// Assignment 2
// This is the main file that the user will be interacting with.
// Interperets user commands and calls methods from TennisDatabase.

import java.util.*;
import TennisDatabase.*;

public class Assignment2
{
   static Scanner scan = new Scanner(System.in);
   static TennisDatabase database = new TennisDatabase();
   static int choice;
   static String id = "unused";
   
   // This displays a menu that informs the user of their options
   // All cases in optionCall are described here
   public static void menu()
   {
      System.out.println( "\n----------- Menu -----------" );
      System.out.println( " 1. Print All Players" );
      System.out.println( " 2. Print Matches of a Player" );
      System.out.println( " 3. Print All Matches" );
      System.out.println( " 4. Insert New Player" );
      System.out.println( " 5. Insert New Match" );
      System.out.println( " 6. Exit" );
      System.out.println( " 7. Print a Player" );
      System.out.println( " 8. Delete a Player" );
      System.out.println( " 9. Clear the Database" );
      System.out.println( "10. Import from File" );
      System.out.println( "11. Export to File" );
      System.out.print( "\nPlease put the number of your choice here > " );
      // I was aware of this error but never knew you have to consume the token with scan.next()
      try { choice = scan.nextInt(); } // Handles when user inputs a letter
      catch ( InputMismatchException e ) { scan.next(); System.out.println( "Please input a number." ); menu(); }
      optionCall(choice);
   }
   
   public static void optionCall( int choice )
   {
      switch( choice )
      {
         case 1:
            System.out.print( "\n" );
            database.printAllPlayers();
            System.out.print( "\n" );
            menu();
            break;
         
         case 2:
            System.out.print( "Please input the id for your player here > " );
            id = scan.next();
            System.out.print( "\n" );
            database.printMatchesOfPlayer(id);
            System.out.print( "\n" );
            menu();
            break;
            
         case 3:
            System.out.print( "\n" );
            database.printAllMatches();
            System.out.print( "\n" );
            menu();
            break;
            
         case 4:
            option4();
            break;
            
         case 5:
            option5();
            break;
            
         case 6:
            System.exit(0);
            break;
         
         case 7:
            System.out.print( "Please input the player's id here > " );
            id = scan.next();
            System.out.print( "\n" );
            database.printPlayer(id);
            System.out.print( "\n" );
            menu();
            break;
         
         case 8:
            System.out.print( "Please input the player's id here > " );
            id = scan.next();
            System.out.print( "\n" );
            database.printPlayer(id);
            database.printMatchesOfPlayer(id);
            database.deletePlayer(id);
            System.out.println( "\n\n^ The above items have been deleted. ^" );
            menu();
            break;
            
         case 9:
            database.reset();
            menu();
            break;
            
         case 10:
            System.out.print( "Please type the name of your .txt file here (do not include the .txt) > " );
            String inFile = scan.next();
            System.out.print( "\n" );
            database.loadFromFile( inFile + ".txt" );
            menu();
            break;
            
         case 11:
            System.out.print( "Please type the name of your .txt file here (do not include the .txt) > " );
            String outFile = scan.next();
            System.out.print( "\n" );
            database.saveToFile( outFile + ".txt" );
            menu();
            break;
            
         default:
            System.out.print( "Please put the number of your choice here > " );
            choice = scan.nextInt();
            optionCall(choice);
      }
      
      
   }
         
   public static void option4()
   {
      int birthYear;
      System.out.print( "Please input the 5 character id for your player here > " );
      String id = scan.next();
      System.out.print( "Please input your player's first name here (letters only) > " );
      String firstName = scan.next();
      System.out.print( "Please input your player's last name here (letters only) > " );
      String lastName = scan.next();
      System.out.print( "Please input the year your player was born here (numbers only) > " );
      try { birthYear = scan.nextInt(); } // Handles when user inputs a letter
      catch ( InputMismatchException e ) { birthYear = -1; }
      System.out.print( "Please input the country your player is from here (letters only) > " );
      String country = scan.next();
      
      database.insertPlayer( id, firstName, lastName, birthYear, country );
      menu();
   }
   
   public static void option5()
   {
      int year;
      int month;
      int day;
      System.out.print( "Please input the id of an existing player here > " );
      String idPlayer1 = scan.next();
      System.out.print( "Please input the id of a different existing player here > " );
      String idPlayer2 = scan.next();
      System.out.print( "Please input the year of this match here (numbers only) > " );
      try { year = scan.nextInt(); } // Handles when user inputs a letter
      catch ( InputMismatchException e ) { year = -1; scan.next(); }
      System.out.print( "Please input the month of this match here (numbers only) > " );
      try { month = scan.nextInt(); } // Handles when user inputs a letter
      catch ( InputMismatchException e ) { month = -1; scan.next(); }
      System.out.print( "Please input the day of this match here (numbers only) > " );
      try { day = scan.nextInt(); } // Handles when user inputs a letter
      catch ( InputMismatchException e ) { day = -1; scan.next(); }
      System.out.print( "Please input the tournament of this match here (letters only) > " );
      String tournament = scan.next();
      System.out.print( "Please input the score of this match here (ex. \"6-7,2-4,5-1,0-2\") > " );
      String score = scan.next();
      
      database.insertMatch( idPlayer1, idPlayer2, year, month, day, tournament, score );
      menu();
   }
      
   public static void main ( String [] args )
   {
      // Loads the menu on start
      optionCall(10);
      menu();
   }
}