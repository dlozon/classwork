import java.util.*;
import BoardObject.Board;

public class NQueens
{
   // Returns an int from the user
   public static int getUserInput()
   {
      Scanner scan = new Scanner(System.in);
      int input = 0;
      
      try { input = scan.nextInt(); } // Handles when user inputs a letter
      catch ( InputMismatchException e ) { scan.next(); System.out.println( "Please input a number." ); getUserInput(); }
      return input;
   }
   
   // Prints the menu to the console
   public static void menu( Board board )
   {
      System.out.println( "\nWhat would you like to do?" );
      System.out.println( " 1. Find a solution using iterative repair" );
      System.out.println( " 2. Find the amount of boards tested by the iterative method" );
      System.out.println( " 3. Find a solution using the exhaustive method" );
      System.out.println( " 4. Find the average amount of boards tested by the exhaustive method");
      System.out.println( " WARNING: average of exhaustive may take time for the computer to find." );
      switch( getUserInput() )
      {
         case 1:
            iterativeSearch(board);
            System.out.println( Arrays.toString(board.getArray()));
            break;
         case 2:
            System.out.println( iterativeSearch(board));
            break;
         case 3:
            exhaustiveSearch(board);
            System.out.println( Arrays.toString(board.getArray()));
            break;
         case 4:
            System.out.println( averageExhaustive(board));
            break;
         default:
            System.out.println( "\nPlease input a valid number.\n" );
            menu(board);
      }
   }
   
   // Moves the last index of an array to [0]
   // pushes the rest of the elements forward
   // [0, 1, 2] --> [2, 0, 1] --> [1, 2, 0]
   public static int[] rotate( int[] array )
   {
      int temp = array[array.length-1];
      
      for( int i = array.length-1; i > 0; i-- )
         array[i] = array[i-1];
      array[0] = temp;
      
      return array;
   }
   
   // Swaps the values of 2 indexes of an array
   public static int[] swap( int[] array, int index1, int index2 )
   {
      if( index2 >= array.length )
         index2 = 0;
      int temp = array[index2];
      array[index2] = array[index1];
      array[index1] = temp;
      return array;
   }
   
   // Returns the average number of boards
   // checked by exhaustiveSearch()
   // over the course of 100,000 executions
   // ***This should be lowered at high values (n > 10)
   public static int averageExhaustive( Board board )
   {
      int count = 0;
      for( int i = 0; i < 100000; i++ )
      {
         board.reset();
         count += exhaustiveSearch(board);
      }
      return count/100000;
   }
   
   // Tests various board configurations until it
   // finds a board with no attacks
   public static int exhaustiveSearch( Board board )
   {
      Random rand = new Random();
      int count = 0;
      int swaps = 0;
      
      for( int i = 0; i < board.getSize(); i++ )
         swaps += i;
         
      while( board.anyQueenAttacked() )
      {
         board.setArray(rotate(board.getArray()));
         board.setArray(swap(board.getArray(), rand.nextInt(board.getSize()), rand.nextInt(board.getSize())));
         for( int i = 0; i < swaps+1; i++ )
         {
            for( int j = 0; j < board.getSize(); j++ )
            {
               count++;
               board.setArray(swap(board.getArray(), j, j+1));
               if(!(board.anyQueenAttacked()))
                  return count;
            }
          }
      }
               return 0; // Should never be reached
   }
   
   // Checks if a swap would reduce attacks on the board,
   // then decides whether or not to perform the swap
   public static int iterativeSearch( Board board )
   {
      Board temp = new Board(board.getSize());
      int count = 0;
      while( board.anyQueenAttacked() )
      {
         for( int i = 0; i < board.getSize(); i++ )
         {
            temp = board;
            if( temp.queenIsAttacked(i) > 0 )
            {
               count++;
               temp.setArray(swap(temp.getArray(), i, i+1));
               if( temp.attacks() < board.attacks() )
                  board = temp;
            }
         }
      }
      return count;
   }
   
   // Driver
   public static void main( String [] args )
   {  
      int n = -1;
      
      while( n < 4 )
      {
         System.out.print( "Please input the size of your chess board (minimum size is 4) > " );
         n = getUserInput();
      }
      
      Board board = new Board(n);
      
      menu(board);
   }
}