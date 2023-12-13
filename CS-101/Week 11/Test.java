import java.util.*;

public class Test
{ 
   public static void main( String[] args ) throws Exception 
   {  
      Scanner scan = new Scanner(System.in);
      
      System.out.print("\nPlease input a number > ");
      int num = scan.nextInt();
      
      int reverse = 0;
      int remainder;
      int ogNum = num;

      ogNum = num;

      while ( num > 0 )
      {
         remainder = num % 10;
         reverse = ( reverse * 10 ) + remainder;
         num /= 10;
      }
      
      System.out.print( ogNum + " backwards is " + reverse + "." );
   } 
}