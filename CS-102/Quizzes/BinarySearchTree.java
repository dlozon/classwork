// Dylan Lozon Quiz 4
// 12/1/2020

public class BinarySearchTree
{
   public static class Node
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
   
   // Inserts a node with given value
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
   
   // Only used for "delete" method to determine successor
   static private Node smallest( Node node ) 
   {  
      if ( node.left != null )  
         return smallest( node.left );  
      else  
         return node;  
   }
   
   // Removes a node with a given value and recconects the tree if need be
   static public Node delete( Node node, int value )
   {
      if ( node == null )
      {
         return null;
      }
      
      else
      {
         if ( value < node.data )
            node.left = delete(node.left, value);
            
         else if ( value > node.data )
            node.right = delete(node.right, value);
            
         else
         {
            if ( node.left == null && node.right == null )
               node = null;
  
            else if ( node.left == null )
               node = node.right;
                  
            else if ( node.right == null )
               node = node.left;
                  
            else 
            {
               Node temp = smallest(node.right);
               node.data = temp.data;
               node.right = delete(node.right, temp.data);
            }
         }
         return node;
      }
   }
   
   // Displays items in postorder
   static public void postorderTraversal( Node node ) 
   {
      
		if ( node !=  null )
      {
			postorderTraversal( node.left );
			postorderTraversal( node.right );
			System.out.print( node.data + " " );
		}
	}
   
   // Counts the nodes on a level
   // Graded for Exercise 1
   static public int numItemsAtLevel( Node node, int count, int level )
   {
      if ( node == null || level < 0 ) 
         return 0;
      if ( count == level )
         return 1;

      return ( numItemsAtLevel(node.right, count + 1, level) + numItemsAtLevel(node.left, count + 1, level)); 
   }
   

   
   public static void main ( String [] args )
   {
      insert(64);
      insert(25);
      insert(91);
      insert(52);
      insert(36);
      insert(79);
      insert(48);
      insert(83);
      insert(17);
      
      System.out.println( "Items inserted: 64, 25, 91, 52, 36, 79, 48, 83, 17\n" );
      
      System.out.println( "         64         " );
      System.out.println( "       /    \\      " );
      System.out.println( "      /      \\     " );
      System.out.println( "     25      91     " );
      System.out.println( "    /  \\     /     " );
      System.out.println( "   17  52   79      " );
      System.out.println( "       /     \\     " );
      System.out.println( "      36     83     " );
      System.out.println( "       \\           " );
      System.out.println( "       48           " );
      
      System.out.print( "\nItems in postorder traversal: " );
      postorderTraversal(root);
      
      System.out.println( "\nNumber of items on level 0: " + numItemsAtLevel(root,0,0) );
      System.out.println( "Number of items on level 1: " + numItemsAtLevel(root,0,1) );
      System.out.println( "Number of items on level 2: " + numItemsAtLevel(root,0,2) );
      System.out.println( "Number of items on level 3: " + numItemsAtLevel(root,0,3) );
      System.out.println( "Number of items on level 4: " + numItemsAtLevel(root,0,4) );
      System.out.println( "Number of items on level 5: " + numItemsAtLevel(root,0,5) );
      System.out.print( "\n" );
      
      delete(root, 64);
      System.out.println( "    [64 REMOVED]  \n" );
      
      System.out.println( "         79         " );
      System.out.println( "       /    \\      " );
      System.out.println( "      /      \\     " );
      System.out.println( "     25      91     " );
      System.out.println( "    /  \\     /     " );
      System.out.println( "   17  52   83      " );
      System.out.println( "       /            " );
      System.out.println( "      36            " );
      System.out.println( "       \\           " );
      System.out.println( "       48         \n" );
      
      System.out.print( "Items in postorder traversal: " );
      postorderTraversal(root);
      
      System.out.println( "\nNumber of items on level 0: " + numItemsAtLevel(root,0,0) );
      System.out.println( "Number of items on level 1: " + numItemsAtLevel(root,0,1) );
      System.out.println( "Number of items on level 2: " + numItemsAtLevel(root,0,2) );
      System.out.println( "Number of items on level 3: " + numItemsAtLevel(root,0,3) );
      System.out.println( "Number of items on level 4: " + numItemsAtLevel(root,0,4) );
      System.out.print( "Number of items on level 5: " + numItemsAtLevel(root,0,5) );
   }
}