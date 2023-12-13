// Dylan Lozon Final
// 12/19/20
// Exercise 1

class LinkedList
{
   static class Node
   {
      int data;
      Node next;
      Node( int n )
      {
         data = n;
         next = null;
      }
   }
   
   static Node first;
   static Node last;
   static int count = 0;
   
   // Adds a node to the list
   static void add( int n )
   {
      Node newNode = new Node(n);
      
      if ( first == null )
      {
         first = last = newNode;
         count++;
      }
      else
      {
         last.next = newNode;
         last = newNode;
         newNode.next = first;
         count++;
      }
   }
   
   // Prints the contents of the list to the console
   static void printList()
   {
      Node current = first;
      
      if ( count == 0 )
         System.out.println( "List is empty" );
      else
      {
         for ( int i = 0; i < count; i++ )
         {
            System.out.print( current.data + ", " );
            current = current.next;
         }
         System.out.print( "\n" );
      }
   }
   
   // Returns the node at an index
   static Node get( int n )
   {
      int i = 0;
      Node current = first;
      
      if ( n > count-1 || n < 0)
         System.out.println( "Index given is out of bounds; node could not be retrieved" );
      
      else
      {
         while ( i < n )
         {
            i++;
            current = current.next;
         }
         return current;
      }
      return null; // should never be reached
   }
   
   // Removes the node at a certain index
   static void removeAtIndex( int n )
   {
      int i = 0;
      Node current = first;
      
      if ( n > count-1 || n < 0 )
         System.out.println( "Index given is out of bounds; removal not completed" );
      
      else
      {
         while ( i < n )
         {
            i++;
            current = current.next;
         }
         
         if ( current == first && current == last )
         {
            first = null;
            last = null;
            current = null;
         }
         else if ( current == last )
         {
            last = get(n-1);
            last.next = first;
         }
         else if ( current == first )
         {
            first = first.next;
            last.next = first.next;
         }
         else
            get(n-1).next = get(n+1);
         count--;
      }
   }
   
   // Deletes the nodes in a range of indexes
   static void deleteMulti( int start, int finish )
   {
      // Switches the values of start and finsh if start > finish
      if ( start > finish )
      {
         int temp = start;
         start = finish;
         finish = temp;
      }
      
      for ( int i = start; i <= finish; i++ )
         removeAtIndex(start);
   }
   
   public static void main( String [] args )
   {
      // fills the list 1-10 and prints it
      add(0);
      add(1);
      add(2);
      add(3);
      add(4);
      add(5);
      add(6);
      add(7);
      add(8);
      add(9);
      printList();
      
      // deletes 3, 4, and 5 and reprints the list
      deleteMulti(3,5); // deleteMulti(5,3); would have the same effect here.
      printList();
      
      // deletes everything left in the list and prints again
      deleteMulti(0,6);      
      printList();
   }
}