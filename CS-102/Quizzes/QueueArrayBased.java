// Dylan Lozon
// CS 102 Quiz 3
import java.util.*;
public class QueueArrayBased
{
   static int[] arrayQueue = new int[9];
   static int size = 0;
   static int start = 0;
   static int end = -1;
   
   // reminder to self:
   // this is the method that will be graded
   // do NOT use "new" in this method
   
   // objects/variables needed
   int[] arrayQueue;
   int size;
   int start;
   int end;
   // methods needed
   public int peek( int [] array ); // method returns the element at the front of an array
   public void doubleSize( int [] array ); // method doubles the size of an array
   public void enqueue( int [] array, int value ); // method adds an element "value" to the back of an array
   public int dequeue( int [] array ); // method removes an element from the front of an array, then returns the element that was removed
   
   // moves the queue so that the starting element is at index 0
   public void reset()
   {
      try
      {
         // loop runs while the front element is not in index 0
         while ( peek(arrayQueue) != arrayQueue[0] )
         {
            if ( size == arrayQueue.length ) // checks if the queue is too large
               doubleSize(arrayQueue);
               
            // dequeue method removes the front element from an arrayQueue
            // next, dequeue returns the element that was removed
            // enqueue method then adds the removed element to the back of the queue
            // this process repeats until the front element is at index 0
            enqueue( arrayQueue, dequeue(arrayQueue));
         }
      }
      catch ( NullPointerException e ) { System.out.println( "Array is empty." ); }
   }
   
   // adds an element to the "end" of the queue
   public static void enqueue(int value)
   {
      arrayQueue[end + 1] = value;
      size++;
      end = size - 1;
   }
   
   // removes an element from the "front" of the queue
   // returns the element that was removed
   public static int dequeue()
   {
      int r = arrayQueue[start];
      arrayQueue[start] = 0;
      arrayQueue[start] = arrayQueue[start + 1];
      for ( int i = 0; i < size - 1; i++ )
         arrayQueue[start + i] = arrayQueue[start + i + 1];
      return r;
   }
   
   public static void main( String [] args )
   {
      enqueue(1);
      enqueue(2);
      enqueue(3);
      enqueue(4);
      enqueue(5);
      enqueue(6);
      enqueue(7);
      enqueue(8);
      enqueue(9);
      //start = arrayQueue[4];
      //end = arrayQueue[3];
      System.out.println(dequeue());
      System.out.println("5 is the starting element");
      System.out.println("before reset: " + Arrays.toString(arrayQueue));
      reset();
      System.out.print("after reset: " + Arrays.toString(arrayQueue));
   }
}