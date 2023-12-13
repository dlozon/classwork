//Dylan Lozon
//Quadratic Formula
import javax.swing.JOptionPane;

public class quadForm 
{
   public static void main( String [] args ) 
   {
      String aStr = JOptionPane.showInputDialog( null, "Enter a-value: ", "quadForm Input", JOptionPane.QUESTION_MESSAGE );
      String bStr = JOptionPane.showInputDialog( null, "Enter b-value: ", "quadForm Input", JOptionPane.QUESTION_MESSAGE );
      String cStr = JOptionPane.showInputDialog( null, "Enter c-value: ", "quadForm Input", JOptionPane.QUESTION_MESSAGE );
      
      double a = Double.parseDouble( aStr );
      double b = Double.parseDouble( bStr );
      double c = Double.parseDouble( cStr );
      
      double disc = Math.pow( b,2 ) - ( 4 * a * c );
      double plus = ( -b + Math.sqrt( disc ) ) / ( 2 * a );
      double minus = ( -b - Math.sqrt( disc ) ) / ( 2 * a );
      
      System.out.println( plus );
      System.out.println( minus );
   }
}