// Dylan Lozon Quiz 2
// 11/3/20

import java.util.*;

public class Node
{
	String data;
	Node next;
	Node( String d )
	{
		data = d;
		next = null;
	}
}

class LinkedList
{
	static Node head;
   
	public void add( String newData )
	{
		Node newNode = new Node( newData );
      
		newNode.next = head;
      
		head = newNode;
	}
   
	public static int search( String value ) // iterative
	{  
      int index = 0; // starts at 0 to be consistent with java index notation
		Node current = head; // initialize current
      
		while ( current != null ) // while there are more items in the list
		{ 
			if ( current.data == value )
         {
            System.out.println( "The first index for " + value + " is: " + index + "." );
				return index;
         }
         
			current = current.next;
         index += 1; // adds 1 after each failed check
		} 
      System.out.println( "Index for this value was not found." ); // error message
      return -1;
	}
   
   public static int recSearch( Node head, String value ) // recursive
   {
      int index = 1;
      Node current = head; // initialize current
      
      if ( current == null ) // base case
      {
         System.out.println( "Index for this value was not found." ); // error message
         return -9999; // ensures that output is negative
      }
      if ( current.data == value )
      {
         System.out.println( "The first index for " + value + " is:" );
         return 0;
      }
        
      return index + recSearch( current.next, value ); // recur until value is found or the end of the list
   }
   
	public static void main( String [] args ) 
	{
   
		LinkedList list = new LinkedList();
      
		list.add( "hello" ); // 3
      list.add( "wrong" ); // 2
      list.add( "string" ); // 1
      list.add( "123" ); // 0
      
      System.out.println( search( "hello" ));
      System.out.println( search( "Wrong" )); // used to test when a value is entered that isn't in the list
      System.out.println( search( "string" ));
      System.out.println( search( "123" ));
      
      System.out.println( "\n-----------------------------------\n" ); // just here for readability in output
      
      System.out.println( recSearch( list.head,"hello" ));
      System.out.println( recSearch( list.head,"Wrong" )); // used to test when a value is entered that isn't in the list
      System.out.println( recSearch( list.head,"string" ));
      System.out.print( recSearch( list.head,"123" ));
	} 
} 