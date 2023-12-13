import java.util.*;

public class q3p2
{
   Stack stack2 = new Stack();
   
   // this code requires that you import java.util.Stack
   // variables/objects required
   int [] queue1;
   Stack stack2;
   int [] queue3;
   int queue1Start;
   int queue1Size;
   int queue3Size;
   
   // methods required
   public void enqueue( int [] array, int value ); // method adds an element "value" to the back of an array
   public void doubleSize( int [] array ); // method doubles the size of an array
   
   public void exercise2();
   {
      for ( i = queue1Start; i < queue1Size; i++ )
         if ( queue1[i] != 123 ) // only executes when element is not 123
            stack2.push(queue1[i]); // fills a stack with all non-123 elements
      
      while ( stack2.empty == false )
      {
         if ( queue3Size == queue3.length )
            doubleSize(queue3);
            
         // stacks are FILO while queues are FIFO so stack2 is opposite order of queue1
         // stack.pop returns the value it removes and we add that value to the back of queue3
         enqueue(queue3, stack2.pop())
         // queue3 should now be the inverse of queue1 and not contain any 123's
         // you could also empty queue1 and put it here instead of queue3
      }
   }
   
   public static void main( String [] args )
   {
      
   }
}