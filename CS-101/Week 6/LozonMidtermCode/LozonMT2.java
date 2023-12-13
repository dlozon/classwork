//Lozon Midterm 2
//Write a method to add the digits of a number  ex 932 = 14

import java.util.Scanner; 

public class LozonMT2
{
   public static int DigitSum ( int number )
   {
      return ( number%10 + (number/10)%10 + number/100 );
   }
   
   public static void main ( String [] args )
   {
      Scanner scan =  new Scanner( System.in );
      
      System.out.print( "Please input a number from 0 - 999 > " );
      int input = scan.nextInt( );
      System.out.print( "The sum of the digits of the number you entered is " + DigitSum( input ));
   }
}