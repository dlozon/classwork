//Dylan Lozon
//Final Exam CS101
 
import java.util.Scanner; 
import java.util.Arrays;

public class LozonFinal
{ 
   
   public static boolean checkPassword()
   {
      Scanner scan = new Scanner( System.in );
      int digits = 0;
      int letters = 0;
      
      System.out.print( "Input a password > " );
      String pass = scan.nextLine();
      
      for ( int i = 0; i < pass.length(); i++)
      {
         if ( Character.isDigit( pass.charAt(i)) == true )
         {
            digits++;
         }
         else if ( Character.isLetter( pass.charAt(i)) == true )
         {
            letters++;
         }
         else if ( Character.isDigit( pass.charAt(i)) == false && Character.isLetter( pass.charAt(i)) == false )
         {
            System.out.println( "This password is not valid\n" );
            return false;
         }
      }
      
      if ( digits < 2 || digits + letters < 8 )
      {
         System.out.println( "This password is not valid\n" );
         return false;
      }
      
      System.out.println( "This password is valid\n" );
      return true;
   }
   
   public static void palPrime()
   {
      int j = 1;
      for ( int i = 0; j < 100; i++)
      {
         if ( isPrime(i) == true && isPalindrome(i) == true )
         {
            System.out.println( i + " is a palindromic prime number." );
            j++;
         }
      }
   }
   
   public static boolean isPrime( int num )
   {
      if ( num <= 1 ) 
         return false; 
        
      for (int i = 2; i < num; i++) 
         if (num % i == 0) 
            return false; 
  
        return true; 
   }
   
   public static boolean isPalindrome( int num )
   {
      int reverse = 0;
      int remainder;
      int ogNum = num;

      ogNum = num;

      while ( num > 0 )
      {
         remainder = num % 10;
         reverse = ( reverse * 10 ) + remainder;
         num /= 10;
      }
      
      if ( ogNum == reverse )
         return true;
      else
         return false;
   }
   
   public static void letterCount()
   {
      Scanner scan = new Scanner(System.in);
      
      System.out.print("\nInput a string consisting of only letters > ");
      
      String input = scan.nextLine();
      
      int [] counts = new int [26];
      char [] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
      
      for ( int i = 0; i < input.length(); i++ )
         counts[input.charAt(i) - 'a']++;
      
      for ( int i = 0; i < 26; i++ )
      {
         if ( counts[i] == 1 )
            System.out.println( "There is " + counts[i] + " " + alphabet[i] + " in your string." );
         else if ( alphabet[i] == 's' && counts[i] != 1 )
            System.out.println( "There are " + counts[i] + " " + alphabet[i] + "' in your string." );
         else
            System.out.println( "There are " + counts[i] + " " + alphabet[i] + "'s in your string." );
      }
   }
   
   public static void inputIsPalindrome()
   {
      Scanner scan = new Scanner(System.in);
      
      System.out.print("\nInput a string consisting of only letters > ");
      String input = scan.nextLine();
      
      char [] reverse = new char [input.length()];
      char [] inputArray = new char [input.length()];
      
      for ( int i = 0; i < input.length(); i++ )
         inputArray[i] = input.charAt(i);
      
      for ( int i = 0; i < input.length(); i++ )
         reverse[input.length()-1-i] = input.charAt(i);
      
      if ( Arrays.equals( reverse, inputArray ))
         System.out.println( input + " is a palindrome.\n" );
      else
         System.out.println( input + " is not a palindrome.\n" );
   }
   
   public static void numberReverse()
   {
      Scanner scan = new Scanner(System.in);
      
      System.out.print("Please input a number > ");
      int num = scan.nextInt();
      
      int reverse = 0;
      int remainder;
      int ogNum = num;

      ogNum = num;

      while ( num > 0 )
      {
         remainder = num % 10;
         reverse = ( reverse * 10 ) + remainder;
         num /= 10;
      }
      
      System.out.print( ogNum + " backwards is " + reverse + "." );
   }
   
   public static void main( String[] args ) 
   {  
      checkPassword();
      palPrime();
      letterCount();
      inputIsPalindrome();
      numberReverse();
   } 
}