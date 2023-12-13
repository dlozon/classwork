//Lozon Miderm 1
//Design a quadric equation class

import java.util.Scanner; 

public class QuadraticEquation
{
   private double a;
   private double b;
   private double c;
   
   public QuadraticEquation( double a, double b, double c )
   {
      this.a = a;
      this.b = b;
      this.c = c;
   }
   
   public double getA()
   {
      return a;
   }

   public double getB()
   {
      return b;
   }

   public double getC()
   {
      return c;
   }
   
   public double getDiscriminant()
   {
      return( Math.pow( b,2 )-4*a*c ); 
   }
   
   public double getRoot1()
   {
      return(( -b + Math.sqrt( getDiscriminant( ))) / ( 2*a ));
   }
   
   public double getRoot2()
   {
      return(( -b - Math.sqrt( getDiscriminant( ))) / ( 2*a ));
   }
   
   public static void main ( String [] args )
   {
      Scanner scan =  new Scanner( System.in );
      
      System.out.println( "Please input your variables a, b, and c as integers one at a time in that order" );
      double a = scan.nextDouble( );
      double b = scan.nextDouble( );
      double c = scan.nextDouble( );
      
      QuadraticEquation quadratic = new QuadraticEquation( a,b,c );
      
      System.out.println( "The discriminant is " + quadratic.getDiscriminant( ));
      
      if ( quadratic.getDiscriminant( ) > 0 )
      {
         System.out.println( "Your 2 roots are " + quadratic.getRoot1( ) + " and " + quadratic.getRoot2( ));
      }
      
      else if ( quadratic.getDiscriminant( ) == 0 )
      {
         System.out.println( "Your root is " + quadratic.getRoot1( ));
      }
      
      else
      {
         System.out.println( "These numbers have no roots" );
      }
   }
}