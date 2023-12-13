//Lozon Midterm 1
//Write a method to covert from F to C

import java.util.Scanner; 

public class LozonMT1
{
   public static double Convert ( double fahrenheit )
   {
      double conversion = (double)5/9;
      double celcius = fahrenheit -32;
      celcius *= conversion;
      return ( celcius );
   }
   
   public static void main ( String [] args )
   {
      Scanner scan =  new Scanner( System.in );
      
      System.out.print( "Please input your temperature in fahrenheit > " );
      double input = scan.nextDouble( );
      System.out.print( "Your temperature in celcius is " + Convert( input ) );
   }
}