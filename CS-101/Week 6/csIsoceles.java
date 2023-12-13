public class csIsoceles
{
   public static boolean Isoceles ( int s1, int s2, int s3 )
   {
        return ( s1 == s2 && s1 != s3 ) || ( s2 == s3 && s2 != s1 ) || ( s1 == s3 && s1 != s2 );
   }
   
   public static void main ( String [] args )
   {
      System.out.println( Isoceles( 1,1,2 ));
      System.out.println( Isoceles( 1,2,1 ));
      System.out.println( Isoceles( 2,1,1 ));
      System.out.println( Isoceles( 1,1,1 ));
      System.out.print( Isoceles( 1,2,3 ));
   }
}