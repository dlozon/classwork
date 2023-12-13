// Dylan Lozon

package ClosestPair;

// Imports
import java.util.*;
import java.lang.Math.*;

public class ClosestPair
{
   int g = 0;
   // Creates a list of n length and generates unique
   // random x,y coordinates until the list is full 
   public Point[] generateRandom( int n, int low, int high )
   {  
      // Variable declarations
      high++;
      boolean flag;
      int index = 0;
      int range = high - low;
      
      // Checks that high > low
      if( range <= 1 )
      {
         System.out.println( "ERR @ generateRandom:\n"+
         "Upper bound must be greater than lower bound.\n"+
         "To resolve: make arg3 a larger number than arg2.\n" );
         return null;
      }
      
      ArrayList<Point> list = new ArrayList<Point>(n);
      
      // Checks that there are enough unique combinations of
      // x and y values in the range to fill the list
      if( n > possibilities( low, high-1 ) )
      {
         System.out.println( "ERR @ generateRandom:\n"+
         "Not enough possible points to fill the list.\n"+
         "To resolve: make arg1/arg2 smaller or make arg3 larger./n" );
         return null;
      }
      
      // Populates the list with random coordinates
      while( list.size() < n )
      {
         flag = false;
         Point newPoint = randomize( low, high );
         
         // flag is lifted if the value is already in the list
         for( int i = 0; i < list.size(); i++ )
            if( newPoint.getX() == list.get(i).getX() && newPoint.getY() == list.get(i).getY() )
               flag = true;
         
         if( flag == false )   
         {
            list.add(newPoint);
            index++;
         }   
      }
      
      // Convert result
      // ArrayList<Point> -> Point[]
      Point[] array = new Point[list.size()];
      array = list.toArray(array);
      
      return array;
   }
   
   // Returns the amount of possible points within a range
   // example: low = 1 and right = 2
   // (1,1),(1,2),(2,1),(2,2) -> 4 possibilities
   private double possibilities( int low, int right )
   {
      int range = right - low + 1;
      double possibilities = 0;
      
      possibilities = Math.pow( range, 2 );
      
      return possibilities;
   }
   
   // Returns one random point within a range
   public Point randomize( int low, int right )
   {
      Random rand = new Random();
      int range = right - low;
      Point pt = new Point( rand.nextInt(range) + low, rand.nextInt(range) + low );
      
      return pt;
   }
   
   // Returns the distance between 2 points
   public double distance( Point a, Point b )
   {
      return( Math.sqrt(Math.pow(b.getX()-a.getX(),2)+Math.pow(b.getY()-a.getY(),2)) );
   }
   
   // Checks every possible combination of points until
   // it reaches the end of the provided list, then it returns
   // the distance of the closest points. This could be optimized
   // quite significantly, but I've chosen not to for the sake of the
   // assignment. Additionally, this is used as a helper for DnC.
   public double exhaustiveSearch( Point[] list )
   {
      double smallest = Integer.MAX_VALUE;
      
      // Checks each point against each other point
      // looking for a smaller distance than it holds
      for( int i = 0; i < list.length; i++ )
         for( int j = 0; j < list.length; j++ )
            if( distance(list[i], list[j]) < smallest && distance(list[i], list[j]) != 0.0 )
               smallest = distance(list[i], list[j]);
      return smallest;
   }
   
   // Calls Divide and Conquer Search
   public double dncSearch( Point[] array )
   {
         Sort sorter = new Sort();
         
         Point[] mSorted = array;
         sorter.mergeSort( mSorted, 0, array.length-1 );
         
         Point[] qSorted = array;
         sorter.quickSort( qSorted, 0, array.length-1 );
         
         System.out.println(g);
         return dncSearch( qSorted, mSorted );
   }
   

   // Uses the Divide n' Conquer method to efficiently
   // search the provided list for the pair of points
   // closest to one another.
   // P must have ascending x values and
   // Q must have ascending y values
   private double dncSearch( Point[] P, Point[] Q )
   {     
      if( P.length <= 3 ) // Base case
         return exhaustiveSearch(P);
      else
      {
         int mid = P.length/2;
         Point[] pLeft = Arrays.copyOfRange( P, 0, mid );
         Point[] pRight = Arrays.copyOfRange( P, mid+1, P.length-1 );
         
         Point[] qLeft = Arrays.copyOfRange( Q, 0, mid );
         Point[] qRight = Arrays.copyOfRange( Q, mid+1, Q.length-1 );
         
         g++;
         double dLeft = dncSearch( pLeft, qLeft );
         double dRight = dncSearch( pRight, qRight );
         
         double d = Math.min( dLeft, dRight);
         
         int m = P[mid-1].getX();
         
         ArrayList<Point> sList = new ArrayList<Point>();
         for( int i = 0; i < Q.length; i++ )
            if( Q[i].getX()-m < d )
               sList.add(Q[i]);
         
         Point[] s = new Point[sList.size()];
         s = sList.toArray(s);
         
         double dminsq = Math.pow(d,2);
         int k = 0;
         for( int i = 0; i < s.length-2; i++ )
         {
            k = i + 1;

            do
            {
               dminsq = Math.min(Math.pow(s[k].getX() - s[i].getX(), 2) + Math.pow(s[k].getY() - s[i].getY(), 2), dminsq);
               k++;
            } while( k <= s.length-1 && Math.pow(s[k].getY() - s[i].getY(), 2) < dminsq );
            
         }
         return Math.sqrt(dminsq);
      }
   }
}