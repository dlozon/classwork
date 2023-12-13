public class EvenlyDivisible
{
   public static boolean evenlyDivisible ( int num1, int num2 )
   {
        return ( num1 % num2 == 0 || num2 % num1 == 0 );
   }
   
   public static void main ( String [] args )
   {
      System.out.println( evenlyDivisible( 4, 2 ));
      System.out.println( evenlyDivisible( 2, 4 ));
      System.out.println( evenlyDivisible( 4, 3 ));
      System.out.print( evenlyDivisible( 3, 4 ));
   }
}