//Dylan Lozon
//Volume of a sphere
import javax.swing.JOptionPane;

public class sphereVolume
{
   public static void main ( String [] args )
   {
      String radStr = JOptionPane.showInputDialog( null, "Enter radius: ", "LectureTimeInSeconds Input", JOptionPane.QUESTION_MESSAGE );
      
      double radius = Double.parseDouble( radStr );
      
      double volume = ( 4.0/3.0 ) * Math.PI * Math.pow( radius , 3 );
      
      System.out.println("The volume is " + volume );
   }
}