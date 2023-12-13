//Dylan Lozon
import javax.swing.JOptionPane;

public class LectureTimeInSeconds
{
   public static void main ( String [] args )
   {
      String timeHStr = JOptionPane.showInputDialog( null, "Enter the hour of the time: ", "LectureTimeInSeconds Input", JOptionPane.QUESTION_MESSAGE );
      String timeMStr = JOptionPane.showInputDialog( null, "Enter the minutes of the time: ", "LectureTimeInSeconds Input", JOptionPane.QUESTION_MESSAGE );
      String timeSStr = JOptionPane.showInputDialog( null, "Enter the seconds of the time: ", "LectureTimeInSeconds Input", JOptionPane.QUESTION_MESSAGE );
      
      int timeH = Integer.parseInt( timeHStr );
      int timeM = Integer.parseInt( timeMStr );
      int timeS = Integer.parseInt( timeSStr );
      
      int timeF = ( timeH * 3600 ) + ( timeM * 60 ) + timeS;
      System.out.println( "timeF = " + timeF );
   }
}