// Dylan Lozon

package ClosestPair;

import java.util.*;

// An object that represents
// a single cartesian point
// on a 2D plane
public class Point implements Comparable<Point>
{
   // Initailize variables
   private final int x;
   private final int y;
   
   // Constructor
   public Point( int x, int y )
   {
      this.x = x;
      this.y = y;
   }
   
   // Accessor methods
   public int getX(){ return this.x; }
   public int getY(){ return this.y; }
   
   // Returns the point as a string
   public String toString(){ return "(" + x + ", " + y + ")" ;}
   
   // Defines comparison for point objects
   @Override
   public int compareTo(Point o)
   {
      if( this.x == o.getX() )
         return Integer.compare(this.y, o.getY());
      return Integer.compare(this.x, o.getX());
   }
   
   // Gives a higher priority to
   // comparing y values
   public int yCompareTo(Point o)
   {
      if( this.y == o.getY() )
         return Integer.compare(this.x, o.getX());
      return Integer.compare(this.y, o.getY());
   }
}