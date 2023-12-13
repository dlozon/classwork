//Dylan Lozon
//Triangle area from sides
import java.util.Scanner;

public class TriangleAreaUsingScanners
{
   public static void main( String [] args ) 
   {
      Scanner scan =  new Scanner( System.in );
      
      System.out.println( "Input the first side of the triangle > " );
      double s1 = scan.nextDouble( );
      
      System.out.println( "\nInput the second side of the triangle > " );
      double s2 = scan.nextDouble( );
      
      System.out.println( "\nInput the final side of the triangle > " );
      double s3 = scan.nextDouble( );
      
      double s = (s1+s2+s3)/2;
      double finalAnswer = Math.sqrt(s*(s-s1)*(s-s2)*(s-s3));
      
      System.out.println( "The area of your triangle is: " + finalAnswer );
   }
}