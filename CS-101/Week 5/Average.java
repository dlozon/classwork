public class Average
{
   public static double average ( double num1, double num2 )
   {
        return ( (num1+num2)/2 );
   }
   
   public static double average ( double num1, double num2, double num3 )
   {
        return ( (num1+num2+num3)/3 );
   }

   
   public static void main ( String [] args )
   {
      System.out.println( average( 1,2 ));
      System.out.print( average( 1,2,3 ));
   }
}