//Dylan Lozon Homework 7
//Make a bank account interface

import java.util.Scanner;

public class LozonHw7
{
   static Scanner scan = new Scanner(System.in);
   static private int id = 378;
   static private boolean account = false;
   static Account acc = new Account( "name", 11111, 1111111111, 0, id );
   
   public static void Menu()
   {
      System.out.println( "-Menu-\n" +
                          "o - Open new account\n" +
                          "t - perform a Transaction on an account\n" +
                          "q - quit" );
                          
      char input = UserInput();
      
      if ( input == 'o' )
      {
         OpenAccount();
         Menu();
      }
      
      else if ( input == 't' )
      {
         if ( account == true  )
         {
            System.out.print( "For security purposes you must enter either your id number or zip code to continue. > " );
            int pass = scan.nextInt();
            if (( pass == acc.getId()) || ( pass == acc.getZip()))
            {
               TMenu();
            }
            else
            {
               System.out.println( "Incorrect. id is " + acc.getId() + " zip is " + acc.getZip() );
               Menu();
            }
         }
         else
         {
            System.out.println( "You must have an account to access this menu.\n" );
            Menu();
         }
      }
      
      else if ( input == 'q' )
         System.exit(0);
      else
         Menu();
   }
   
   public static void OpenAccount()
   {
      Account acc = new Account( "name", 11111, 1111111111, 0, id );
      acc.setName();
      acc.setZip();
      acc.setPhoneNum();
      
      System.out.print( "How much money will you initialize your account with? > " );
      double amt = scan.nextDouble();
      acc.Deposit(amt);
      
      System.out.println( "Account was successfully created, your id is " + acc.getId() + ", make sure to write it down.\n" );
      id++;
      account = true;
   }
   
   public static void TMenu()
   {
      double amt;
      char input = 'e';
      
      System.out.println( "-Transaction Menu-\n" +
                          "d - Deposit\n" +
                          "w - Withdraw\n" +
                          "e - Enquire balance\n" +
                          "m - Main menu" );
                          
      input = UserInput();
                          
      if ( input == 'd' )
      {
         System.out.println( "How much would you like to deposit?" );
         amt = scan.nextDouble();
         acc.Deposit(amt);
         System.out.println( "You now have $" + acc.getBalance() + " in your account." );
         TMenu();
      }
      else if ( input == 'w' )
      {
         System.out.println( "How much would you like to withdraw?" );
         amt = scan.nextDouble();
         acc.Withdraw(amt);
         System.out.println( "You now have $" + acc.getBalance() + " in your account." );
         TMenu();
      }
      else if ( input == 'e' )
      {
         System.out.println( "You have $" + acc.getBalance() + " in your account." );
         TMenu();
      }
      else if ( input == 'm' )
         Menu();
      else
        TMenu();
   }
   
   public static char UserInput()
   {
      String input = scan.next().toLowerCase();
      return input.charAt(0);
   }
   
   public static void main( String [] args )
   {
      Account acc = new Account( "name", 11111, 1111111111, 0, id );
      Menu();
   }
}