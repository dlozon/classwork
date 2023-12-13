public class test
{
   static int k = 0;
   public static float Alg( int[] A, int l, int r )
   {
      if( l > r ) { return 0.0F; }
      if( l == r ) { return A[l]; }
      else 
      {
         
         int split = (l+r)/2;
         float tmp1 = Alg( A, l, split );
         float tmp2 = Alg( A, split+1, r );
         k++;
         return (float) ( ( tmp1 * (split - l + 1) ) +
                        ( tmp2 * (r - split) ) ) /
                (float) (r - l + 1);
      }
   }
   
   static public void main (String [] args)
   {
      int[] A = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
      
      System.out.print(Alg(A, 17, 17) + " k: " + k);
   }
}