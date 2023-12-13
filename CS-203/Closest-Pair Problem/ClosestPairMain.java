// Dylan Lozon

import ClosestPair.*;
import java.util.*;

public class ClosestPairMain
{
   // Global variables
   private static ArrayList<Point> userList = new ArrayList<Point>();
   private static Point[] randList = new Point[0];
   private static ClosestPair pt = new ClosestPair();
   
   // Returns an int from the user
   private static int getUserInput()
   {
      Scanner scan = new Scanner(System.in);
      int input = 0;
      
      try { input = scan.nextInt(); } // Handles when user inputs a letter
      catch ( InputMismatchException e ) { scan.next(); System.out.println( "Please input an integer." ); getUserInput(); }
      return input;
   }
   
   // Prints a menu to the console and interacts with the user
   public static void menu()
   {
      boolean flag = false;
      int choice = -1;
      
      // These two loops print the two lists above the menu
      System.out.print( "\nYour current list is: [ " );
      for( int i = 0; i < userList.size(); i++ )
      {
         if( i != userList.size()-1 )
            System.out.print( userList.get(i).toString() + ", " );
         else
            System.out.print( userList.get(i).toString() );
      }
      System.out.println( " ]" );
         
      System.out.print( "The current random list is: [ " );
      if( randList != null )
      {
         for( int i = 0; i < randList.length; i++ )
         {
            if( i != randList.length-1 )
               System.out.print( randList[i].toString() + ", " );
            else
               System.out.print( randList[i].toString() );
         }
      }
      System.out.println( " ]" );
      
      // This is the actual menu presented to the user
      System.out.println( "\nWhat would you like to do?" );
      System.out.println( " 1. Add a point to your list" );
      System.out.println( " 2. Generate a random list" );
      System.out.println( " 3. Exhaustively find the distance of the closest pair in a list" );
      System.out.println( " 4. Find the distance of the closest pair in a list with the Divide and Conquer method" );
      
      // Switch acts on the user's decision
      switch( getUserInput() )
      {
         case 1:
            System.out.print( "Please input the x value of your point > " );
            int x = getUserInput();
            System.out.print( "Please input the y value of your point > " );
            int y = getUserInput();
            
            Point newPoint = new Point(x, y);
            for( int i = 0; i < userList.size(); i++ )
               if( userList.get(i).compareTo(newPoint) == 0 )
                  flag = true;
            
            if( flag )
               System.out.print( "\nYour list already contains that point" );
            else
               userList.add(newPoint);
               
            menu();
            break;
            
         case 2:
            System.out.print( "How many points should be generated? > " );
            int n = getUserInput();
            System.out.print( "What should be the lower boundary for point generation? > " );
            int l = getUserInput();
            System.out.print( "What should be the upper boundary for point generation? > " );
            int u = getUserInput();
            
            randList = pt.generateRandom(n, l, u);
            menu();
            break;
            
         case 3:
            System.out.println( "What list would you like to search?" );
            System.out.println( " 1. Your list" );
            System.out.println( " 2. The random list" );
            
            while( choice != 1 && choice != 2 )
               choice = getUserInput();
            
               exhaustive(choice);
            break;
            
         case 4:
            System.out.println( "What list would you like to search?" );
            System.out.println( " 1. Your list" );
            System.out.println( " 2. The random list" );
            
            while( choice != 1 && choice != 2 )
               choice = getUserInput();
            
               dnc(choice);
            break;
         
         // Default case ensures that the choice is between 1 and 4
         default:
            System.out.println( "\nPlease input a valid number.\n" );
            menu();
      }
   }
   
   // Calls exhaustiveSearch to solve the selected list
   private static void exhaustive( int choice )
   {
      if( choice == 1 && userList.size() > 1 )
      {
         Point[] array = new Point[userList.size()];
         array = userList.toArray(array);
         System.out.println( "The shortest distance between two points in your list is: " + pt.exhaustiveSearch(array) + "\n" );
      }
      else if( choice == 2 && randList.length > 1 )
         System.out.println( "The shortest distance between two points in the random list is: " + pt.exhaustiveSearch(randList) + "\n" );
      else
         System.out.println( "That list does not have enough values." );
      
      menu();
   }
   
   // Calls dncSearch to solve the selected list
   private static void dnc( int choice )
   {
      if( choice == 1 && userList.size() > 1 )
      {
         Point[] array = new Point[userList.size()];
         array = userList.toArray(array);
         System.out.println( "The shortest distance between two points in your list is: " + pt.dncSearch(array) + "\n" );
      }
      else if( choice == 2 && randList.length > 1 )
         System.out.println( "The shortest distance between two points in the random list is: " + pt.dncSearch(randList) + "\n" );
      else
         System.out.println( "That list does not have enough values." );
      
      menu();
   }
   
   // Driver code
   public static void main( String [] args )
   {
      menu();
   }
}