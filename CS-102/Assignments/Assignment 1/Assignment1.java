import java.util.*;
import TennisDatabase.*;

public class Assignment1
{
   static Scanner scan = new Scanner(System.in);
   static TennisDatabase database = new TennisDatabase();
   static int choice;
   static String id = "unused";
   
   public static void onStart()
   {
      System.out.println( "Please type the name of your .txt file here (do not include the .txt) > " );
      
      String file = scan.nextLine();
      database.loadFromFile( file + ".txt" );
   }
   
   public static void menu()
   {
      System.out.println( "\n----------- Menu -----------" );
      System.out.println( "1. Print All Players" );
      System.out.println( "2. Print Matches of a Player" );
      System.out.println( "3. Print All Matches" );
      System.out.println( "4. Insert New Player" );
      System.out.println( "5. Insert New Match" );
      System.out.println( "6. Exit\n" );
      System.out.print( "Please put the number of your choice here > " );
      choice = scan.nextInt(); 
      optionCall(choice);
   }
   
   public static void optionCall( int choice )
   {
      switch( choice )
      {
         case 1:
            database.printAllPlayers();
            menu();
            break;
         
         case 2:
            System.out.print( "Please input the id for your player here > " );
            id = scan.next();
            System.out.print( "\n" );
            option2(id);
            break;
            
         case 3:
            option3();
            System.out.print( "\n" );
            break;
            
         case 4:
            option4();
            System.out.print( "\n" );
            break;
            
         case 5:
            option5();
            System.out.print( "\n" );
            break;
            
         case 6:
            System.exit(0);
            break;
         
         default:
            System.out.println( "Please put the number of your choice here > " );
            choice = scan.nextInt();
            optionCall(choice);
      }
      
      
   }
   
   public static void option1()
   {
      database.printAllPlayers();
      menu();
   }
   
   public static void option2( String id )
   {
      database.printMatchesOfPlayer(id);
      menu();
   }
   
   public static void option3()
   {
      database.printAllMatches();
      menu();
   }
   
   public static void option4()
   {
      System.out.print( "Please input the 5 character id for your player here > " );
      String id = scan.next();
      System.out.print( "Please input your player's first name here (letters only) > " );
      String firstName = scan.next();
      System.out.print( "Please input your player's last name here (letters only) > " );
      String lastName = scan.next();
      System.out.print( "Please input the year your player was born here (numbers only) > " );
      int birthYear = scan.nextInt();
      System.out.print( "Please input the country your player is from here (letters only) > " );
      String country = scan.next();
      
      database.insertPlayer( id, firstName, lastName, birthYear, country );
      menu();
   }
   
   public static void option5()
   {
      System.out.print( "Please input the id of an existing player here > " );
      String idPlayer1 = scan.next();
      System.out.print( "Please input the id of a different existing player here > " );
      String idPlayer2 = scan.next();
      System.out.print( "Please input the year of this match here (numbers only) > " );
      int year = scan.nextInt();
      System.out.print( "Please input the month of this match here (numbers only) > " );
      int month = scan.nextInt();
      System.out.print( "Please input the day of this match here (numbers only) > " );
      int day = scan.nextInt();
      System.out.print( "Please input the tournament of this match here (letters only) > " );
      String tournament = scan.next();
      System.out.print( "Please input the score of this match here (ex. \"6-7,2-4,5-1,0-2\") > " );
      String score = scan.next();
      
      database.insertMatch( idPlayer1, idPlayer2, year, month, day, tournament, score );
      menu();
   }
   
   public static void main ( String [] args )
   {
      onStart();
      System.out.println(database.getWinLoss("NAD86"));
      menu();
      
   }
}