//Lozon Midterm 4
//Write a method to give the factorial of a number

import java.util.Scanner; 

public class LozonMT4
{
   public static int Factorial ( int number )
   {
      int factorial = 1;
      
      if ( number > 0 )
      {
         for ( int i = number; i > 0; i-- )
         {
            factorial *= i;
         }
         
         return ( factorial );
      }
      
      else
      {
         System.out.print( "The number you enter should be greater than 0" );
         System.exit(0);
         return( 0 );
      }
   }
   
   public static void main ( String [] args )
   {
      Scanner scan =  new Scanner( System.in );
      
      System.out.println( "Please input a non-zero positive number and keep in mind " );
      System.out.print( "that numbers above 12 may be too large to print properly > " );
      int input = scan.nextInt( );
      System.out.print( "The factorial of the number you entered is " + Factorial( input ));
   }
}