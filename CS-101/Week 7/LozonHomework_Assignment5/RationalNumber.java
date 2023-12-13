//Dylan Lozon Homework 5
//Design a rational number object and give methods to interact with it

public class RationalNumber
{
   private int numerator;
   private int denominator;
   public RationalNumber( int numerator, int denominator )
   {
      this.numerator = numerator;
      this.denominator = denominator;
      
      if ( denominator == 0 )
      {
         System.out.print( "Error: Divide by 0" );
         System.exit(0);
      }
      
      format();
   }
   
   public void format()
   {
      int numerator = getNumerator();
      int denominator = getDenominator();
      
      if ( numerator < 0 && denominator < 0 )
      {
         numerator = Math.abs( numerator );
         denominator = Math.abs( denominator );
      }
      
      else if ( numerator < 0 || denominator < 0 )
      {
         numerator = 0 - Math.abs( numerator );
         denominator = Math.abs( denominator );
      }
      setNumerator( numerator );
      setDenominator( denominator );
   }
   
   public void add( int num )
   {
      int numerator = getNumerator();
      int denominator = getDenominator();
      
      numerator += ( num * denominator );
      setNumerator( numerator );
   }
   
   public void subtract( int num )
   {
      int numerator = getNumerator();
      int denominator = getDenominator();
      
      numerator -= ( num * denominator );
      setNumerator( numerator );
   }
   
   public void multiply( int num )
   {
      this.numerator *= num;
   }
   
   public void divide( int num )
   {
      this.numerator /= num;
   }
   
   public void reciprocal( )
   {
      int numerator = getNumerator();
      int denominator = getDenominator();
      
      setNumerator( denominator );
      setDenominator( numerator );
      format();
   }
   
   private static int gcd( int a, int b ) 
   {
      if ( b == 0 )
      {
         return a;
      }
      
      else
      {
         return gcd( b, a % b );
      }   
   }

   public String toString( ) 
   {
      int numerator = getNumerator();
      int denominator = getDenominator();
      int gcd = gcd( numerator, denominator );
      
      if ( gcd < 0 )
         gcd = Math.abs(gcd);
      
      if ( denominator == 0 )
         return "undefined";
      
      if ( denominator / gcd == 1 )
         return ( numerator / gcd + "" );
      
      setNumerator( numerator );
      setDenominator( denominator );
      format();
      return (( numerator / gcd ) + "/" + ( denominator / gcd ));
   }
   
   public double toDouble()
   {
      double numerator = getNumerator();
      double denominator = getDenominator();
      
      return numerator/denominator;
   }
   
   public boolean equals( double test )
   {
      return ( toDouble() == test );
   }
   
   public int getNumerator( )
   {
      return this.numerator;
   }
   
   public int getDenominator( )
   {
      return this.denominator;
   }
   
   public void setNumerator( int numerator )
   {
      this.numerator = numerator;
   }
   
   public void setDenominator( int denominator )
   {
      this.denominator = denominator;
   }
   
   public static void main( String [] args ) 
   {
      RationalNumber num = new RationalNumber( 1, 2 );
      
      System.out.println( "Rational number is " + num.toString());
      
      num.add(3);
      System.out.println( "After adding 3 the rational number is " + num.toString());
      
      num.subtract(2);
      System.out.println( "After subtracting 2 the rational number is " + num.toString());
      
      num.multiply(5);
      System.out.println( "After multiplying the rational number by 5 the rational number is " + num.toString());
      
      num.divide(3);
      System.out.println( "After dividing the rational number by 3 the rational number is " + num.toString());
      
      num.reciprocal();
      System.out.println( "The reciprocal of the rational number is " + num.toString());
      
      System.out.println( "The rational number in decimal form is " + num.toDouble());
      
      System.out.println( "It is " + num.equals(.4) + " that " + num.toDouble() + " is equal to .4" );
      
      System.out.print( "It is " + num.equals(.5) + " that " + num.toDouble() + " is equal to .5" );
   }
}