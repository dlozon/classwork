//Dylan Lozon
import javax.swing.JOptionPane;

public class triangleArea
{
   public static void main( String [] args ) 
   {
      String s1Str = JOptionPane.showInputDialog( null, "Enter s1: ", "triangle Input", JOptionPane.QUESTION_MESSAGE );
      String s2Str = JOptionPane.showInputDialog( null, "Enter s2-value: ", "triangle Input", JOptionPane.QUESTION_MESSAGE );
      String s3Str = JOptionPane.showInputDialog( null, "Enter s3-value: ", "triangle Input", JOptionPane.QUESTION_MESSAGE );
      
      double s1 = Double.parseDouble( s1Str );
      double s2 = Double.parseDouble( s2Str );
      double s3 = Double.parseDouble( s3Str );
      
      double s = ( s1 + s2 + s3 ) / 2;
      double finalAnswer = Math.sqrt(s*(s-s1)*(s-s2)*(s-s3));
      
      System.out.println( finalAnswer );
   }
}