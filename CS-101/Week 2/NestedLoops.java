public class NestedLoops
{
   public static void main ( String [] args )
   {
      for ( int line = 1; line < 6; line++ )
         {
            for ( int num = line; num < line * 6; num = num + line )
            {
               System.out.print( num + " " );
            }
            System.out.print( "\n" );
         }
   }
}