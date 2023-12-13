import javax.swing.JOptionPane;

public class Exercise2_1 {
   // Main method
   public static void main(String[] args) {
      //Enter a temp in Fahrenheit
      String fahrenheitString = JOptionPane.showInputDialog(null,"Enter a temperature in fahrenheit:","Exercise2_1 Input", JOptionPane.QUESTION_MESSAGE);
         
      //COnvert string to double
      double fahrenheit = Double.parseDouble(fahrenheitString);

      //Convert it to Celsius
      double celsius = (5.0/9.0) * (fahrenheit - 32);
      
      //Display the result
      JOptionPane.showMessageDialog(null, "The temperature is " +
         celsius + " in Celsius"); 
   }
}           