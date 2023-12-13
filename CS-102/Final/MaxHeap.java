// Dylan Lozon Final
// 12/19/20
// Exercise 3

public class MaxHeap 
{
   private int[] heap;
   private int size;
   private int maxSize;
  
   public MaxHeap( int max )
   {
      maxSize = max;
      size = 0;
      heap = new int[maxSize + 1];
      heap[0] = Integer.MAX_VALUE;
   }
   
   // Inserts a new element to max heap
   public void insert( int value )
   {
      heap[++size] = value;
      
      int current = size;
      while ( heap[current] > heap[ parent( current )])
      {
         swap( current, parent(current));
         current = parent(current);
      }
   }
   
   // Removes an element
   public int delete()
   {
      int removedValue = heap[1];
      heap[1] = heap[size--];
      fixSubtree(1);
      return removedValue;
   }
   
   // Prints the values stored by listing the children of a parent position
   public void print()
   {
      for ( int i = 1; i <= size / 2; i++ ) 
      {
         System.out.print( "Parent: " + heap[i] + " Left child: " + heap[2 * i] + " Right child: " + heap[2 * i + 1]);
         System.out.print( "\n" );
      }
   }
   
   // Returns the position of a value's parent, right child, or left child, respectively
   private int parent( int position ) { return position / 2; }
   private int leftChild( int position ) { return 2 * position; }
   private int rightChild( int position ) { return 2 * position + 1; }
   
   // Returns true if given node is leaf
   private boolean isLeaf( int position )
   {
      if ( position >= size / 2 && position <= size )
         return true;
      
      return false;
   }
   
   // Corrects the subtree of a position 
   private void fixSubtree( int position )
   {
      if ( isLeaf(position) == true )
         return;
      
      if ( heap[position] < heap[ leftChild( position )] || heap[position] < heap[rightChild( position )])
      {
         if ( heap[ leftChild( position )] > heap[ rightChild( position )])
         {
            swap(position, leftChild(position));
            fixSubtree(leftChild(position));
         }
         else 
         {
            swap(position, rightChild(position));
            fixSubtree(rightChild(position));
         }
      }
   }
   
   // Swaps the value of 2 positions
   private void swap( int first, int second )
   {
      int tmp;
      tmp = heap[first];
      heap[first] = heap[second];
      heap[second] = tmp;
   }
   
   public static void main( String[] args )
   {
      MaxHeap heap = new MaxHeap(300);
      
      heap.insert(133);
      heap.insert(155);
      heap.insert(177);
      heap.insert(111);
      heap.insert(166);
      
      System.out.println( "Here is the current tree\n" );
      
      System.out.println( "          177         " );
      System.out.println( "        /     \\      " );
      System.out.println( "       /       \\     " );
      System.out.println( "     166       111    " );
      System.out.println( "    /   \\            " );
      System.out.println( "  133   155           " );
      
      System.out.println( "\n---- Deleting 177 ----" );
      heap.delete();
      
      System.out.println( "Here is the new tree\n" );
      
      System.out.println( "          166         " );
      System.out.println( "        /     \\      " );
      System.out.println( "       /       \\     " );
      System.out.println( "     155       111    " );
      System.out.println( "     /                " );
      System.out.println( "   133                " );
      
      System.out.println( "\n----- Adding 122 -----" );
      heap.insert(122);
      System.out.println( "----- Adding 199 -----" );
      heap.insert(199);
      System.out.println( "----- Adding 188 -----" );
      heap.insert(188);
      System.out.println( "----- Adding 144 -----" );
      heap.insert(144);
      
      System.out.println( "Here is the tree after additions\n" );
      
      System.out.println( "            199            " );
      System.out.println( "          /     \\         " );
      System.out.println( "         /       \\        " );
      System.out.println( "        /         \\       " );
      System.out.println( "      155          188     " );
      System.out.println( "     /   \\        /  \\   " );
      System.out.println( "    144  122    111   166  " );
      System.out.println( "   /                       " );
      System.out.println( " 133                       " );
      
      System.out.println( "\n---- Deleting 199 ----" );
      heap.delete();
      System.out.println( "Here is the final tree\n" );
      
      System.out.println( "           188            " );
      System.out.println( "         /     \\         " );
      System.out.println( "        /       \\        " );
      System.out.println( "      166       155       " );
      System.out.println( "     /   \\      /  \\    " );
      System.out.println( "    111  133  122   144   " );
   }
}