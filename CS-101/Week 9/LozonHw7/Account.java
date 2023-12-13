//Dylan Lozon Homework 7
//Make a bank account interface

import java.util.Scanner;

public class Account
{
   Scanner scan = new Scanner(System.in);
   
   private String name;
   private int zip;
   private long phoneNum;
   private double balance;
   private int id;
   
   public Account( String name, int zip, int phoneNum, double balance, int id)
   {
      this.id = id;
      this.name = name;
      this.zip = zip;
      this.phoneNum = phoneNum;
      this.balance = balance;
   }
   
   public int getId( )
   {       
      return this.id;
   }
   
   public void setName( )
   {
      System.out.print( "Please input your first and last name > " );
      String newName = scan.nextLine();
      
      String[] name = newName.replaceAll( "[^a-zA-Z ]", "" ).trim( ).split( "\\s+" );
      
      if ( name.length == 2 )
      {   
         this.name = newName;
         System.out.println( "Your name was successfully changed to " + newName + ".\n" );
      }
      
      else
      {
         System.out.println( "Make sure that you've only input two words; your first and last names separated by a space." );
         setName();
      }
   }

   public String getName( )
   {
      return this.name;
   }
   
   public void setZip( ) 
   {
      System.out.print( "Please input your 5 digit zip code > " );
      int newZip = scan.nextInt();
      
      int digits = String.valueOf(newZip).length();
      
      if ( digits <= 5 )
      {
         zip = zip;
         System.out.println( "Your zip code was successfully changed to " + newZip + ".\n" );
      }
      
      else
      {
         System.out.println( "Please input a valid zip code." );
         setZip();
      }
   }

   public int getZip( )
   {
      return zip;
   }
   
   public void setPhoneNum( ) 
   {
      System.out.print( "Please input your 10 digit phone number with no formatting (parentheses, dashes, etc.) > " );
      long newPhoneNum = scan.nextLong();
      
      int digits = String.valueOf(newPhoneNum).length();

      if ( digits == 10 )
      {
         this.phoneNum = newPhoneNum;
         System.out.println( "Your phone number was successfully changed to " + newPhoneNum + ".\n" );
      }
      
      else
      {
         System.out.println( "Please input a valid phone number." );
         setPhoneNum();
      }
   }

   public long getPhoneNum( )
   {
      return this.phoneNum;
   }
   
   public String PhoneNumStr( )
   {
      String numStr = String.valueOf(phoneNum);
      
      char[] numbers = new char[numStr.length()];
      
      for ( int i = 0; i < 10; i++ )  
            numbers[i] = numStr.charAt(i);
      
      return ("(" + numbers[1] + numbers[2] + numbers[3] + ") " + numbers[4] + numbers[5] + numbers[6] + "-" + numbers[7] + numbers[8] + numbers[9] + numbers[10] );
   }
   
   public double getBalance( ) 
   {
      return this.balance;
   }
   
   public void Deposit( double num )
   {
      balance += num;
   }
   
   public void Withdraw( double num)
   {
      if ( balance >= num )
         balance -= num;
      else
         System.out.println( "You do not have enough money to withdraw that amount." );
   }
}