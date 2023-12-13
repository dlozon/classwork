// TennisMatch object

package TennisDatabase;

// Imports for use in the "processMatchScore" method
import java.lang.String;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class TennisMatch implements TennisMatchInterface
{
   // Declare variables
   private final String idPlayer1;
   private final String idPlayer2;
   private final int dateYear;
   private final int dateMonth;
   private final int dateDay;
   private final String tournament;
   private final String matchScore;
   private int currentSetIndex;
   private String currentSetScore;
   private int gamesWonByPlayer1;
   private int gamesWonByPlayer2;
   
   public TennisMatch ( String idPlayer1, String idPlayer2, int dateYear, int dateMonth, int dateDay, String tournament, String matchScore )
   {
      this.idPlayer1 = idPlayer1;
      this.idPlayer2 = idPlayer2;
      this.dateYear = dateYear;
      this.dateMonth = dateMonth;
      this.dateDay = dateDay;
      this.tournament = tournament;
      this.matchScore = matchScore;
   }
   
   // Accessor methods
   public String getIdPlayer1() { return this.idPlayer1; }
   public String getIdPlayer2() { return this.idPlayer2; }
   public int getDateYear() { return this.dateYear; }
   public int getDateMonth() { return this.dateMonth; }
   public int getDateDay() { return this.dateDay; }
   public String getTournament() { return this.tournament; }
   public String getMatchScore() { return this.matchScore; }
   public int getWinner() { return recProcessMatchScoreCall(); } // Returns the winner of the match as a 1 or 2
   public int getDateInt() { return (dateYear * 10000) + (dateMonth * 100) + dateDay; } // Returns the date as an integer
   
   // Compares matches based on date
   // Returns -1 when older than comparison, 1 when newer, and 0 if on the same day
   public int compareTo( TennisMatch o ) 
   {
      int date = o.getDateInt();
      int newDate = getDateInt();
      
      if (date > newDate)
         return -1;
      else if (date == newDate)
         return 0; 
      else
         return 1; 
   }  
   
   public int recProcessMatchScoreCall() // this method is just a call for recProcessMatchScore
   {
      currentSetIndex = 0;
      int winner = recProcessMatchScore();
      
      
      if ( winner > 0 ) // if player 1 won, winner will be greater than 0
         return 1;      // if player 2 won, winner will be less than 0
      else              // winner should never == 0 because of the exception handling in recProcessMatchScore
         return 2;
   }
   
   private int recProcessMatchScore()
   {
      if( this.matchScore.length() == 0 ) { 
         // Error: input match score is empty, raise a runtime error (exception).
         throw new TennisDatabaseRuntimeException( "Match score processing: match score is empty!" );
      }
      
      else 
      {
         if ( currentSetIndex == ( this.matchScore.length()+1)/4 )
            return 0; // Since this.matchScore is formatted as "x-y," every 4 characters is 1 game
                      // then we add 1 to the length to account for the missing comma on the last set "x-y"
      
         Scanner scoreScanner = new Scanner(this.matchScore).useDelimiter(","); // Scanner to parse the input match score.
         currentSetIndex++; // Index of currentent set (1, 2, 3 etc.).
         
         for ( int i = 1; i <= currentSetIndex; i++ )
            currentSetScore = scoreScanner.next(); // This loop scans the list until it finds the "index" of the current match
         
         Scanner currentSetScanner = new Scanner(currentSetScore).useDelimiter("-"); // Scanner to parse current set score.
         gamesWonByPlayer1 = currentSetScanner.nextInt(); // Count games won by player 1.
         gamesWonByPlayer2 = currentSetScanner.nextInt(); // Count games won by player 2.
            
      }
      
      // if player 1 wins, add 1 to the total by returning +1
      if ( gamesWonByPlayer1 > gamesWonByPlayer2 )
         return recProcessMatchScore() + 1; 
                   
      // if player 2 wins, subtract 1 from the total by returning -1
      else if ( gamesWonByPlayer1 < gamesWonByPlayer2 ) 
         return recProcessMatchScore() - 1; 
                  
      else // Current set is a draw, invalid result, raise a runtime error (exception).
         throw new TennisDatabaseRuntimeException( "Match score processing: set " + currentSetIndex + " score is invalid!" );
   }
   
   public void displayAsString()
   {
      System.out.println( "MATCH/" + this.idPlayer1 + "/" + this.idPlayer2 + "/" + getDateInt() + "/" + this.tournament + "/" + this.matchScore );
   }
}