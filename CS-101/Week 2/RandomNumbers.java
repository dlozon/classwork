import java.util.Random;

public class RandomNumbers
{
   public static void main( String [] args )
   {
      Random random = new Random( );
      
      int die = random.nextInt( 6 ) + 1;
      System.out.println( "\nThe die roll is " + die );
      
      int start = 20, end = 200;
      int number = random.nextInt( end - start + 1 ) + start;
      System.out.println( "\nThe random number between " + start + " and " + end + " is " + number );
   }
}