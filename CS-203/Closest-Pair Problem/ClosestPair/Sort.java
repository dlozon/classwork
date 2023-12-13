// Dylan Lozon

package ClosestPair;

import java.util.*;

// Object that contains different
// mutative sortation methods
public class Sort
{
   // Uses mergesort to mutatively sort the provided array 
   // with the help of the merge method below, this
   // function does the bulk of the actual sortation
   public void mergeSort( Point array[], int left, int right )
   {
      if( left < right ) 
      {
         int mid = left + ( right - left ) / 2;
         
         // Sort first and second halves
         mergeSort(array, left, mid);
         mergeSort(array, mid + 1, right);
         
         // Merge the sorted halves
         merge(array, left, mid, right);
      }
   }
   
   // Uses mergesort to mutatively sort the provided array 
   // with the help of the mergeSort method above, this
   // function does the bulk of the movement within the array
   private void merge( Point array[], int left, int mid, int right )
   {
      int n1 = mid - left + 1;
      int n2 = right - mid;
      
      // Create temporary arrays and copy data
      Point L[] = new Point[n1];
      Point R[] = new Point[n2];
      
      for( int i = 0; i < L.length; i++ )
         L[i] = array[left + i];
         
      for( int j = 0; j < R.length; j++ )
         R[j] = array[mid + 1 + j];
      
      int i = 0;
      int j = 0;
      int k = left;
      
      while( i < n1 && j < n2 ) 
      {
         if( L[i].compareTo(R[j]) == -1 ) 
         {
            array[k] = L[i];
            i++;
         }
         
         else 
         {
            array[k] = R[j];
            j++;
         }
            k++;
      }
      
      // Copy remaining elements of L
      while( i < n1 )
      {
         array[k] = L[i];
         i++;
         k++;
      }
      
      // Copy remaining elements of R
      while( j < n2 )
      {
          array[k] = R[j];
          j++;
          k++;
      }
   }
   
   // Uses quickSort to mutatively sort the provided array 
   // with the help of the partition method below, this
   // function does the bulk of the actual sortation
   public void quickSort( Point[] array, int left, int right)
   {
      if( left < right )
      {
         int partitionIndex = partition(array, left, right);
         
         // Separately sort elements before
         // and after partition
         quickSort(array, left, partitionIndex);
         quickSort(array, partitionIndex + 1, right);
      }
   }
   
   // Uses quickSort to mutatively sort the provided array 
   // with the help of the quickSort method above, this
   // function does the bulk of the partitioning in the array.
   // This function uses Hoare's partition scheme.
   private int partition( Point[] array, int left, int right )
   {
      Point pivot = array[left];
      int i = left - 1;
      int j = right + 1;
      
      while( true )
      {
         // Finds the leftmost element less than the pivot
         do 
         { j--; } while( array[j].yCompareTo(pivot) == 1 );
         
         // Finds the leftmost element greater than the pivot
         do 
         { i++; } while( array[i].yCompareTo(pivot) == -1 );
         
         if( i >= j )
            return j;
         
         // Swaps i with j
         Point temp = array[i];
         array[i] = array[j];
         array[j] = temp;
      }
   }

}