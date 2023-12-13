//Tennis database

package TennisDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TennisDatabase implements TennisDatabaseInterface
{
   TennisPlayerContainer playerContainer = new TennisPlayerContainer();
   TennisMatchContainer matchContainer = new TennisMatchContainer();
   int length; // Used in checking processes
   
   // Declaring variables for instantiating a player
   String id;
   String firstName;
   String lastName;
   int birthYear;
   String country;
   
   // Declaring variables for instantiating a match
   String idPlayer1;
   String idPlayer2;
   int date;
   int dateYear;
   int dateMonth;
   int dateDay;
   String tournament;
   String matchScore;
   
   // Imports data from a txt file
   public void loadFromFile( String fileName )
   {
      try 
      {
         File file = new File(fileName);
         Scanner scan = new Scanner(file);
         scan.useDelimiter("/|\\n"); // uses "/" and new lines as delimiters
         
         while ( scan.hasNext() )
         {
            String dataType = scan.next();
            if (dataType.equals("PLAYER"))
            {
               id = scan.next(); 
               firstName = scan.next();
               lastName = scan.next();
               birthYear = scan.nextInt();
               country = scan.next();
               
               TennisPlayer p = new TennisPlayer( id.toUpperCase(), firstName.toUpperCase(), lastName.toUpperCase(), birthYear, country.toUpperCase() );
               
               insertPlayer(p);
            }
            
            if (dataType.equals("MATCH"))
            {
               idPlayer1 = scan.next(); 
               idPlayer2 = scan.next();
               date = scan.nextInt();
               tournament = scan.next();
               matchScore = scan.next();
               
               // Splitting the date into 3 separate values
               dateDay = date % 100;
               dateMonth = date / 100 % 100;
               dateYear = date / 10000;
               
               TennisMatch m = new TennisMatch( idPlayer1.toUpperCase(), idPlayer2.toUpperCase(), dateYear, dateMonth, dateDay, tournament.toUpperCase(), matchScore );
               
               insertMatch(m);
            }
         }
            scan.close();
      } 
      catch ( FileNotFoundException e ) { System.out.println( "File was not found." ); }
   }  
   
   // Single line methods that only call other methods
   public TennisPlayer getPlayer( String id ) { return playerContainer.getPlayer(id); }
   public TennisPlayer[] getAllPlayers() { return playerContainer.getAllPlayers(); }
   public TennisMatch[] getMatchesOfPlayer( String playerId ) { return playerContainer.getMatchesOfPlayer(playerId); }
   public void printAllPlayers() { playerContainer.printAll(); }
   public void printMatchesOfPlayer( String playerId ) { playerContainer.printMatchesOfPlayer(playerId); }
   public String getWinLoss( String id ) { return playerContainer.getWinLoss(id); }
   public void printAllMatches() { matchContainer.printAllMatches(); }
   public TennisMatch[] getAllMatches() { return matchContainer.getAllMatches(); }
   // Inserts a player, as opposed to a sum of it's paramaters
   public void insertPlayer( TennisPlayer p ) { playerContainer.insertPlayer(p); }
   
   public void insertMatch( TennisMatch m ) 
   { 
      // Checks that both players are in this list
      if ( playerContainer.getPlayer(idPlayer1) == null || playerContainer.getPlayer(idPlayer2) == null )
         System.out.println( "One or more players in this match could not be found; match not added to list." );
      else
      {
         matchContainer.insertMatch(m);
         playerContainer.insertMatch(m);
      } 
   }
   
   public void insertPlayer( String id, String firstName, String lastName, int birthYear, String country )
   {
      if ( checkPlayer( id, firstName, lastName, birthYear, country ) == true )
      {
         TennisPlayer p = new TennisPlayer( id.toUpperCase(), firstName.toUpperCase(), lastName.toUpperCase(), birthYear, country.toUpperCase() );
      
         playerContainer.insertPlayer(p);
      }
      else
         System.out.println( "One or more input is invalid for this player; they have not been added." );
   }
      
   public void insertMatch( String idPlayer1, String idPlayer2, int year, int month, int day, String tournament, String score )
   {
      // Checks that both players are in this list
      if ( playerContainer.getPlayer(idPlayer1) == null || playerContainer.getPlayer(idPlayer2) == null )
      {
         System.out.println( "One or more players in this match could not be found; match not added to list." );
      }
      
      else if ( checkMatch( idPlayer1, idPlayer2, year, month, day, tournament, score ) == true )
      { 
         TennisMatch m = new TennisMatch( idPlayer1.toUpperCase(), idPlayer2.toUpperCase(), year, month, day, tournament.toUpperCase(), score );
      
         matchContainer.insertMatch(m);
         playerContainer.insertMatch(m);
      }
      else
         System.out.println( "One or more input is invalid for this match; they have not been added." );
   }
   
   public boolean checkPlayer( String id, String firstName, String lastName, int birthYear, String country )
   {
      if ( id.length() != 5 )
         return false;
         
      // Check firstName is only letters
      if ( firstName == null ) // checks if the String is null
         return false;
      length = firstName.length();
      for ( int i = 0; i < length; i++ ) 
      {
         if ( Character.isLetter(firstName.charAt(i)) == false )
            return false;
      }
      
      // Check lastName is only letters
      if ( lastName == null ) // checks if the String is null
         return false;
      length = lastName.length();
      for ( int i = 0; i < length; i++ ) 
      {
         if ( Character.isLetter(lastName.charAt(i)) == false )
            return false;
      }
      
      if ( country == null ) // checks if the String is null
         return false;
      length = country.length();
      for ( int i = 0; i < length; i++ ) 
      {
         if ( Character.isLetter(country.charAt(i)) == false )
            return false;
      }
      
      return true;
   }
   
   public boolean checkMatch( String idPlayer1, String idPlayer2, int year, int month, int day, String tournament, String score )
   {
      if ( idPlayer1.equals(idPlayer2) == true )
         return false;
      if ( year > 2020 || year < 0 )
         return false;
      if ( month > 12 || month < 0 )
         return false;
      if ( day > 31 || day < 0 )
         return false;
      // Checks that tornament only consists of letters
      if ( tournament == null ) // checks if the String is null
         return false;
         
      length = tournament.length();
      for ( int i = 0; i < length; i++ ) 
      {
         if ( Character.isLetter(tournament.charAt(i)) == false )
            return false;
      }
      
      if ( score.contains("-") == false )
         return false; 
         
      return true;
   }
}