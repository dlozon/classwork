// Dylan Lozon

package BoardObject;

import java.util.*;

public class Board
{
   // Initailize variables
   private final int size;
   private final int[] boardArray;
   
   // Constructor
   public Board(int size)
   {
      this.size = size;
      boardArray = new int[size];
      
      for( int i = size-1; i >= 0; i-- )
         boardArray[i] = i;
   }
   
   // Accessors
   public int getSize() { return this.size; }
   public int[] getArray() { return this.boardArray; }
   
   // Copies an input array to the board
   public void setArray( int[] array ) 
   {
      if( array.length == this.size )
         for( int i = 0; i < size; i++ )
               boardArray[i] = array[i];
   }
   
   // Sets the board's array to
   // [0, 1, 2, ... size-2, size-1]
   public void reset()
   {
      for( int i = size-1; i >= 0; i-- )
         boardArray[i] = i;
   }
   
   // Check if the queen in a column is being attacked
   // By definition, there will always be exactly one queen
   // per column, and exactly one queen per row
   // so we don't need to check for cardinal attacks
   public int queenIsAttacked( int col )
   {
      int row = boardArray[col];
      int i;
      int j;
      int attacks = 0;
      
      // Check for queens on the upper left diagonal
      for( i = col-1, j = row-1; i <= size-1 && i >= 0 && j <= size-1 && j >= 0; i--, j-- ) 
         if( boardArray[i] == j )
            attacks++;

      
      // Check for queens on the bottom right diagonal
      for( i = col+1, j = row+1; i <= size-1 && i >= 0 && j <= size-1 && j >= 0; i++, j++ ) 
         if( boardArray[i] == j )
            attacks++;
      
      // Check for queens on the bottom left diagonal
      for( i = col-1, j = row+1; i <= size-1 && i >= 0 && j <= size-1 && j >= 0; i--, j++ ) 
         if( boardArray[i] == j )
            attacks++;
      
      // Check for queens on the upper right diagonal
      for( i = col+1, j = row-1; i <= size-1 && i >= 0 && j <= size-1 && j >= 0; i++, j-- ) 
         if( boardArray[i] == j )
            attacks++;
      
      return attacks;
   }
   
   // Returns true if any queen on 
   // the board is under attack
   public boolean anyQueenAttacked()
   {
      for( int i = 0; i < size; i++ )
         if( queenIsAttacked(i) > 0 )
            return true;
      
      return false;
   }
   
   // Returns the number of attacks on the board
   public int attacks()
   {
      int attacks = 0;
      for( int i = 0; i < size; i++ )
         attacks += queenIsAttacked(i);
            
      return attacks;
   }
}