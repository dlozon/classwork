// Dylan Lozon
// CS-102, Fall 2020
// Assignment 2

package TennisDatabase;

// A resizeable circular array queue for use in TennisPlayerContainerIterator
class TennisPlayerQueue implements TennisPlayerQueueInterface
{
   // Declarations
   private int front;
   private int end;
   private int count;
   private TennisPlayer[] queue;
   
   // Constructor
   public void CircularArrayQueue()
   {
      this.front = 0;
      this.end = 0;
      this.count = 0;
      this.queue = new TennisPlayer[1];
   }
   
   // Getter methods
   public int getFront() { return this.front; }
   public int getEnd() { return this.end; }
   public int size() { return this.count; }
   public TennisPlayer get( int i ) { return queue[i]; }
   
   // Adds a player at the end of the queue
   public void enqueue( TennisPlayer p )
   {
      if ( count == queue.length ) 
         doubleSize();

      queue[end] = p;
      end = ( end + 1 ) % queue.length;
      count++;
   }
   
   // Extracts a player at the front of the queue
   public TennisPlayer dequeue()
   {
      if ( isEmpty() == true )
         System.out.println( "Queue is empty." );
         
      TennisPlayer p = queue[front];
      queue[front] = null;

      front = ( front + 1 ) % queue.length;

      count--;

      return p;
   }
   
   // Returns the player at the front of the queue
   public TennisPlayer peek() { return queue[front]; }
   
   // Returns true if the queue is empty and false if it's not
   public boolean isEmpty() { return ( count == 0 ); }
   
   // Doubles the size of the queue
   public void doubleSize()
   {
      TennisPlayer[] temp = new TennisPlayer[count * 2];   
      
      for ( int i = 0; i < count; i++ )
      {
         temp[i] = queue[front];
         front = ( front + 1 ) % queue.length;
      }
      
      front = 0;
      end = count;
      queue = temp;
      temp = null;
   }
}