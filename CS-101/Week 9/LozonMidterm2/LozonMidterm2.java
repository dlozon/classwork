//Lozon Miderm 2
//Design 2 dimensional arrays

import java.util.Random; 
import java.util.Arrays;

public class LozonMidterm2
{
   
   public static int[] DoubleSize( int array[] )
   {
      int size = array.length;
      int [] doubledArray = new int [size*2];
      
      for ( int i = 0; i < array.length; i++ )
      {
         doubledArray[i] = array[i];
      }
      
      return doubledArray;
   }
   
   public static int[] Reverse( int array[] )
   {
      int [] reversedArray = new int [array.length];
      
      for ( int i = 0; i < array.length; i++ )
      {
         reversedArray[array.length-1-i] = array[i];
      } 
      
      return reversedArray;
   }
   
   public static void Count( int array[] )
   {
      int one = 0; int two = 0; int three = 0; int four = 0; int five = 0; int six = 0; int seven = 0; int eight = 0; int nine = 0; int ten = 0;
      
      for ( int i = 0; i < array.length; i++ )
      {
         if ( array[i] == 1 )
            one++;
         if ( array[i] == 2 )
            two++;
         if ( array[i] == 3 )
            three++;
         if ( array[i] == 4 )
            four++;
         if ( array[i] == 5 )
            five++;
         if ( array[i] == 6 )
            six++;
         if ( array[i] == 7 )
            seven++;
         if ( array[i] == 8 )
            eight++;
         if ( array[i] == 9 )
            nine++;
         if ( array[i] == 10 )
            ten++;
      }
      
      System.out.println( "The number one occurs " + one + " times in oneDimArray" );
      System.out.println( "The number two occurs " + two + " times in oneDimArray" );
      System.out.println( "The number three occurs " + three + " times in oneDimArray" );
      System.out.println( "The number four occurs " + four + " times in oneDimArray" );
      System.out.println( "The number five occurs " + five + " times in oneDimArray" );
      System.out.println( "The number six occurs " + six + " times in oneDimArray" );
      System.out.println( "The number seven occurs " + seven + " times in oneDimArray" );
      System.out.println( "The number eight occurs " + eight + " times in oneDimArray" );
      System.out.println( "The number nine occurs " + nine + " times in oneDimArray" );
      System.out.println( "The number ten occurs " + ten + " times in oneDimArray\n" );
      
   }
   
   public static void Position( int array[][], int num )
   {
      for (int i = 0; i < 10; i++) 
      {  
         for (int j = 0; j < 10; j++)
         { 
            if ( array[i][j] == num )
               System.out.println( "The number " + num + " appears at position [" + i + "][" + j + "]" );
         }
      }
   }
   
   public static void main ( String [] args )
   {
      Random rand = new Random();
      
      int [] oneDimArray = new int [10];
      
      for ( int i = 0; i < 10; i++ )
      {
         oneDimArray[i] = rand.nextInt(10)+1;
      }
      System.out.println( "The one dimensional array is " + Arrays.toString( oneDimArray ) + "\n" );
      
      int [][] twoDimArray = new int [10][10]; 
  
      for (int i = 0; i < 10; i++) 
      {  
         for (int j = 0; j < 10; j++)
         { 
            twoDimArray[i][j] = rand.nextInt(10)+1;   
         }
      }
      System.out.println( "The two dimensional array is " + Arrays.deepToString( twoDimArray ) + "\n" );
      
      System.out.println( "The one dimensional array doubled is " + Arrays.toString( DoubleSize( oneDimArray )) + " and it has " + DoubleSize(oneDimArray).length + " elements.\n" );
      
      System.out.println( "The one dimensional array reversed is " + Arrays.toString( Reverse( oneDimArray )) + "\n" );
      
      Count(oneDimArray);
      
      Position(twoDimArray,2);
   }
}