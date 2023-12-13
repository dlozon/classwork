// Dylan Lozon Quiz 1
// 10/20/2020

public class LozonQuiz1
{
   public static int sumPowersOfTwo( int n ) // Iterative method
   {
      int sum = 0; // Total value
      int add = 1; // How much is added to the total each iteration
      int nTemp = n; // Temporary n value that multiplies "add" by 2 until it's decremented to 0
      
      if ( n < 0 ) // checks that n is valid
      {
         System.out.println( "Error: n may not be less than 0" );
         System.exit(0); // Ends run when n is below 0
         return 0; // Should never reach this
      }
      
      while ( n >= 0 ) // Runs when n == 0 so that 1 is added to the total
      {
         nTemp = n;
            
         while ( nTemp > 0 ) // doesn't run when n == 0 or else the total will be doubled
         {
            add *= 2;
            nTemp--;
         }
            
         sum += add;
         add = 1;
         n--;
      }
      return sum;
   }
   
   /* Recursive definition 1,3,7,15,...
      Initial condition: t(0) = 1
      Recursive formula: t(n) = 2^n + t(n-1) */
   
   public static int recSumPowersOfTwo( int n ) // Recursive method
   {
      int nTemp = n;
      int add = 1;
      
      if ( n < 0 ) // checks that n is valid
      {
         System.out.println( "Error: n may not be less than 0" );
         System.exit(0); // Ends run when n is below 0
         return 0; // Should never reach this
      }
      
      while ( nTemp > 0 )
      {
         add *= 2;
         nTemp--;
      }
      
      if ( n == 0 ) // Base case
         return 1;
      else 
         return add + recSumPowersOfTwo(n-1); // Recursive call
   }
   
   public static void main ( String [] args )
   {
      // Each method is tested with the values 0, 1, 2, 3, and 10
      // Results should be 1, 3, 7, 15, and 2047, respectively
   
      System.out.println( sumPowersOfTwo(0) );
      System.out.println( sumPowersOfTwo(1) );
      System.out.println( sumPowersOfTwo(2) );
      System.out.println( sumPowersOfTwo(3) );
      System.out.println( sumPowersOfTwo(10) + "\n");

      System.out.println( recSumPowersOfTwo(0) );
      System.out.println( recSumPowersOfTwo(1) );
      System.out.println( recSumPowersOfTwo(2) );
      System.out.println( recSumPowersOfTwo(3) );
      System.out.print( recSumPowersOfTwo(10) );  
   }
}