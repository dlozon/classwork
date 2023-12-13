//Dylan Lozon Homework 4
//Translate any sentence contaiining only valid pig latin words to english

import java.util.Scanner; 
import java.util.Arrays;

public class LozonHw4_PigToEng
{
   public static void main( String [] args ) 
   {
      Scanner scan =  new Scanner( System.in );
      
      System.out.print( "Please write your pig latin sentence here > " );
      String pigLatin = scan.nextLine( );
      System.out.println( "Input was \"" + pigLatin + "\"" );
      
      String[] words = pigLatin.replaceAll( "[^a-zA-Z ]", "" ).trim( ).split( "\\s+" );
      System.out.println( Arrays.toString( words ) + "\n" );
      
      int amountOfWords = words.length;
      System.out.println( "There are " + amountOfWords + " words\n" );
      
      String currentWord = words[0];
      char lastLetter = currentWord.charAt( currentWord.length()-1 );
      char firstLetter = currentWord.charAt(0);
      String output = "";
      
      
      for ( int i = 0; i < amountOfWords; i++ )
      {
         System.out.print( i + ", " );
         
         currentWord = words[i];
         lastLetter = currentWord.charAt( currentWord.length()-1 );
         firstLetter = currentWord.charAt(0);
         
         if (( ( currentWord.charAt(0) == 'Y' || currentWord.charAt(0) == 'y' )
            && ( currentWord.charAt(1) == 'A' || currentWord.charAt(1) == 'a' )
            && ( currentWord.charAt(2) == 'Y' || currentWord.charAt(2) == 'y' ) )
            &&( lastLetter == 'A' || lastLetter == 'a'
             || lastLetter == 'E' || lastLetter == 'e'
             || lastLetter == 'I' || lastLetter == 'i'
             || lastLetter == 'O' || lastLetter == 'o'
             || lastLetter == 'U' || lastLetter == 'u' ))
         {
            System.out.println( currentWord + " is a valid 'yay-word'" );
            currentWord = currentWord.substring(3, currentWord.length());
            output += currentWord + " ";
         }
         
         else if ((( currentWord.charAt( currentWord.length()-1 ) == 'Q' || currentWord.charAt( currentWord.length()-1 ) == 'q' )
                && ( currentWord.charAt( currentWord.length()-2 ) == 'U' || currentWord.charAt( currentWord.length()-2 ) == 'u' )
                && ( currentWord.charAt( currentWord.length()-3 ) == 'Q' || currentWord.charAt( currentWord.length()-3 ) == 'q' ) )
                &&( firstLetter != 'A' || firstLetter != 'a'
                 || firstLetter != 'E' || firstLetter != 'e'
                 || firstLetter != 'I' || firstLetter != 'i'
                 || firstLetter != 'O' || firstLetter != 'o'
                 || firstLetter != 'U' || firstLetter != 'u' ))
         {
            System.out.println( currentWord + " is a valid 'quq-word'" );
            currentWord = currentWord.substring(1, currentWord.length()-3);
            currentWord += firstLetter;
            output += currentWord + " ";
         }
         
         else
         {
            System.out.println( "The word in this position (" + currentWord + ") is invalid, please make sure you have entered proper pig-latin." );
            System.exit(0);
         }
      }
      
      System.out.print( "\n" );
      System.out.print( "Your input translated to english is " + output );
   }
}