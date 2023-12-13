//Lozon Midterm 3
//Write a method to add all numbers 10 - 100

public class LozonMT3
{
   public static int Sum ()
   {
      int sum = 0;
      for ( int i = 10; i <= 100; i++ )
      {
         sum += i;
      }
      return ( sum );
   }
   
   public static void main ( String [] args )
   {      
      System.out.print( "The sum of the numbers 10 - 100 is " + Sum());
   }
}