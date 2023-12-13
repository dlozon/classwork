// Dylan Lozon Final
// 12/19/20
// Exercise 2

// Class Node and methods insert and postorderTraversal have been reused from a previous quiz.
// As these methods are not the focus of the assignment, 
// and it's my code anyway, I decided not to rewrite them from scratch.

class BST
{
   static class Node
   {
      int data;  
      Node left;  
      Node right;  
      
      public Node( int data )
      {
         this.data = data;
         this.left = null;
         this.right = null;
      }
   }
   
   static Node root;
   static int count = 0;
   
   // Calls numItemsInRange
   static public void numItemsInRange( int min, int max)
   {
      count = 0;
      // Switches the values of min and max if min > max
      if ( min > max )
      {
         int temp = min;
         min = max;
         max = temp;
      }
      
      numItemsInRange( root, min, max );
      
      System.out.println( "There are " + count +
       " items between " + min + " and " + max + "." );
   }
   
   // Calls postorderTraversal
   static public void postorderTraversal()
   {
      postorderTraversal( root );
   }
      
   // Inserts a node into the tree
   static public void insert( int data )
   {
      Node newNode = new Node( data );
     
      if ( root == null )
      {
         root = newNode;
         return;
      }
      
      else
      {
         Node current = root;
         Node parent = null;
         
         while ( true )
         {
            parent = current;
            
            if ( data < current.data )
            {
               current = current.left;
               
               if ( current == null )
               {
                  parent.left = newNode;
                  return;
               }
            }
            
            else
            {
               current = current.right;
               
               if ( current == null )
               {
                  parent.right = newNode;
                  return;
               }
            }
         }
      }
   }
   
   // Prints all items in postorder
   static private void postorderTraversal( Node node ) 
   {
		if ( node !=  null )
      {
			postorderTraversal( node.left );
			postorderTraversal( node.right );
			System.out.print( node.data + " " );
		}
	}
   
   // Prints the number if items in a range, inclusively
   static private void numItemsInRange(Node node, int min, int max) 
   { 
      if ( node == null )
          return;
      
      if ( min < node.data )
          numItemsInRange( node.left, min, max );
      
      if ( min <= node.data && max >= node.data )
          count++;
      
      if ( max > node.data )
          numItemsInRange( node.right, min, max );
   }
   
   public static void main( String [] args )
   {
      insert(0);
      insert(1);
      insert(2);
      insert(3);
      insert(4);
      insert(5);
      insert(6);
      insert(7);
      insert(8);
      insert(9);
      
      System.out.print( "The elements in this list are as follows: ");
      postorderTraversal();
      System.out.print( "\n" );
      
      numItemsInRange(4,7); // numItemsInRange(7,4); would have the same effect here.
   }
}