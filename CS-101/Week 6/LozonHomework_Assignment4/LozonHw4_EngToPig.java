//Dylan Lozon Homework 4
//Translate any sentence from english to pig latin
 
import java.util.Scanner; 
import java.util.Arrays;

public class LozonHw4_EngToPig
{ 
   public static void main( String[] args ) throws Exception 
   {  
      Scanner scan =  new Scanner( System.in );
      
      System.out.print( "Please write your english sentence here > " );
      String english = scan.nextLine( );
      System.out.println( "Input was \"" + english + "\"" );
      
      String[] words = english.replaceAll( "[^a-zA-Z ]", "" ).trim( ).split( "\\s+" );
      System.out.println( Arrays.toString( words ) + "\n" );
      
      int amountOfWords = words.length;
      System.out.println( "There are " + amountOfWords + " words\n" );
      
      String currentWord = words[0];
      char lastLetter = currentWord.charAt( currentWord.length()-1 );
      String output = "";
      
      for ( int i = 0; i < amountOfWords; i++ )
      {
         System.out.print( i + ", " );
         
         currentWord = words[i];
         lastLetter = currentWord.charAt( currentWord.length()-1 );
         
         if (  lastLetter == 'A' || lastLetter == 'a'
            || lastLetter == 'E' || lastLetter == 'e'
            || lastLetter == 'I' || lastLetter == 'i'
            || lastLetter == 'O' || lastLetter == 'o'
            || lastLetter == 'U' || lastLetter == 'u' )
         {
            System.out.println( "The last letter of " + currentWord + " is " + lastLetter + " and is a vowel." );
            currentWord = "yay" + currentWord + " ";
            output += currentWord;
         }
         else
         {
            System.out.println( "The last letter of " + currentWord + " is " + lastLetter + " and is a consonant." );
            
            currentWord = currentWord.substring(0, currentWord.length()-1);
            currentWord = lastLetter + currentWord + "quq ";
            output += currentWord;
         }
      }
      System.out.print( "\n" );
      System.out.print( "Your input translated to pig latin is " + output );
   } 
}