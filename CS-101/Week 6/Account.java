public class Account
{
   private int id;
   private double balance;
   private double annualInterestRate;
   private java.util.Date dateCreated;
   
   public Account( int id, double balance, double annualInterestRate )
   {
      this.id = id;
      this.balance = balance;
      this.annualInterestRate = annualInterestRate;
      dateCreated = new java.util.Date();
   }
   
   public int getId()
   {
      return this.id;
   }

   public double getBalance()
   {
      return balance;
   }

   public double getAnnualInterestRate()
   {
      return annualInterestRate;
   }
   
   public void setId( int id )
   {
      this.id = id;
   }
   
   public void setBalance( double balance )
   {
      this.balance = balance;
   }
   
   public void setAnnualInterestRate( double annualInterestRate )
   {
      this.annualInterestRate = annualInterestRate;
   }
   
   public double getMonthlyInterestRate()
   {
      return balance * ( annualInterestRate / 12.00 );
   }
   
   public java.util.Date getDateCreated()
   {
      return dateCreated;
   }
   
   public void withdraw( double amount )
   {
      balance -= amount;
   }
   
   public void deposit( double amount )
   {
      balance += amount;
   }
   
   public static void main ( String [] args )
   {
      Account account = new Account(1122,20000,4.5);
      
      System.out.println( "Balance is " + account.getBalance());
      account.withdraw(2500);
      System.out.println( "Balance is " + account.getBalance() + " after withdraw" );
      account.deposit(3000);
      System.out.println( "Balance is " + account.getBalance() + " after withdraw" );
      System.out.print( "Date created is " + account.getDateCreated());
   }
}