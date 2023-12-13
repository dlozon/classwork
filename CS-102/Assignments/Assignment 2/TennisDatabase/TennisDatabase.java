// Dylan Lozon
// CS-102, Fall 2020
// Assignment 2

package TennisDatabase;

import java.io.*;
import java.util.*;

// This file is the Database that holds both types of containers
// This file recieves user commands from Assignment2 
// Then it calls the appropriate methods from other files
public class TennisDatabase implements TennisDatabaseInterface
{  
   // Creates an empty container for each type of data
   TennisPlayerContainer playerContainer = new TennisPlayerContainer();
   TennisMatchContainer matchContainer = new TennisMatchContainer();
   int length; // Used in checking processes
   
   // Declares variables for instantiating a player
   String id;
   String firstName;
   String lastName;
   int birthYear;
   String country;
   
   // Declares variables for instantiating a match
   String idPlayer1;
   String idPlayer2;
   int date;
   int dateYear;
   int dateMonth;
   int dateDay;
   String tournament;
   String matchScore;
   
   // Imports data from a .txt file
   public void loadFromFile( String fileName )
   {
      try 
      {
         File file = new File(fileName);
         Scanner scan = new Scanner(file);
         scan.useDelimiter("/|\\n"); // uses "/" and new lines as delimiters
         
         while ( scan.hasNext() )
         {
            String dataType = scan.next().toUpperCase();
            // When "PLAYER" is found in the file, create a player and try to insert it
            if (dataType.equals("PLAYER"))
            {
               id = scan.next(); 
               firstName = scan.next();
               lastName = scan.next();
               try { birthYear = scan.nextInt(); }
               catch ( InputMismatchException e ) { birthYear = -1; }
               country = scan.next();
               
               insertPlayer( id, firstName, lastName, birthYear, country );
            }
            
            // When "MATCH" is found, create a match and try to insert it
            if (dataType.equals("MATCH"))
            {
               idPlayer1 = scan.next(); 
               idPlayer2 = scan.next();
               
               try { date = scan.nextInt(); }
               catch ( InputMismatchException e ) { date = -1; }
               tournament = scan.next();
               matchScore = scan.next();
               
               // Splitting the date into 3 separate values
               dateDay = date % 100;
               dateMonth = (date / 100) % 100;
               dateYear = date / 10000;
               
               insertMatch( idPlayer1, idPlayer2, dateYear, dateMonth, dateDay, tournament, matchScore );
            }
         }
            scan.close();
      }
      catch ( FileNotFoundException e ) { System.out.println( "File was not found." ); }
   }
   
   // Exports current data to a .txt file
   // Will create a file if one does not exist
   // Will overwrite the file if it does exist
   public void saveToFile( String fileName )
   {
      try 
      {
         File file = new File(fileName);
         if ( file.createNewFile() )
           System.out.println( "File created: " + file.getName() );
      } 
      catch (IOException e) { System.out.println( "An error occurred." ); }
      
      try 
      {
         FileWriter writer = new FileWriter( fileName );
         
         TennisPlayer playerList[] = getAllPlayers();
         TennisMatch matchList[] = getAllMatches();
         
         for ( int i = 0; i < getAllPlayers().length; i++ )
            writer.write( playerList[i].toString() + "\n" );
         System.out.println( "Successfully wrote players to " + fileName );
         
         for ( int i = 0; i < getAllMatches().length; i++ )
            writer.write( matchList[i].toString() + "\n" );
         System.out.println( "Successfully wrote matches to " + fileName );
         
         writer.close();
      }
      catch (IOException e) 
      {
         System.out.println( "An error occurred." );
      }
   }
     
   // Single line methods that only exist to call other methods
   public TennisPlayer getPlayer( String id ) { return playerContainer.getPlayer(id); }
   public TennisPlayer[] getAllPlayers() { return playerContainer.getAllPlayers(); }
   public TennisMatch[] getMatchesOfPlayer( String playerId ) { return playerContainer.getMatchesOfPlayer(playerId); }
   public void printAllPlayers() { playerContainer.printAll(); }
   public void printMatchesOfPlayer( String playerId ) { playerContainer.printMatchesOfPlayer(playerId); }
   public void printWinLoss( String id ) { playerContainer.getWinLoss(id); }
   public void printAllMatches() { matchContainer.printAllMatches(); }
   public TennisMatch[] getAllMatches() { return matchContainer.getAllMatches(); }
   public void deletePlayer( String id ) { playerContainer.deletePlayer(id); matchContainer.deleteMatchesOfPlayer(id); }
   public void reset() { playerContainer.reset(); matchContainer.reset(); }
   public void printPlayer( String id ) { playerContainer.printPlayer(id); }
   
   // Checks that a player is valid and inserts them into the container
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
   
   // Checks that a match is valid and inserts them into the container
   public void insertMatch( String idPlayer1, String idPlayer2, int year, int month, int day, String tournament, String score )
   {
      // Preliminarily checks that both players exist
      if ( playerContainer.getPlayer(idPlayer1.toUpperCase()) == null || playerContainer.getPlayer(idPlayer2.toUpperCase()) == null )
         System.out.println( "One or more players in this match could not be found; match not added to list." );
      
      else if ( checkMatch( idPlayer1, idPlayer2, year, month, day, tournament, score ) == true )
      { 
         TennisMatch m = new TennisMatch( idPlayer1.toUpperCase(), idPlayer2.toUpperCase(), year, month, day, tournament.toUpperCase(), score );
      
         matchContainer.insertMatch(m);
         playerContainer.insertMatch(m);
      }
      else
         System.out.println( "One or more input is invalid for this match; they have not been added." );
   }
   
   // Validates a player
   public boolean checkPlayer( String id, String firstName, String lastName, int birthYear, String country )
   {
      if ( id.length() != 5 )
         return false;
         
      // Check firstName is only letters
      if ( firstName == null ) // checks if the String is null
         return false;
      length = firstName.length();
      for ( int i = 0; i < length; i++ ) 
         if ( Character.isLetter(firstName.charAt(i)) == false )
            return false;
      
      // Check lastName is only letters
      if ( lastName == null ) // checks if the String is null
         return false;
      length = lastName.length();
      for ( int i = 0; i < length; i++ ) 
         if ( Character.isLetter(lastName.charAt(i)) == false )
            return false;
      
      // Check that player has been born
      if ( birthYear < 0 || birthYear > 2020 )
         return false;
      
      // Check country is only letters and spaces
      if ( country == null ) // checks if the String is null
         return false;
      length = country.length();
      for ( int i = 0; i < length-1; i++ ) 
         if ( Character.isLetter(country.charAt(i)) == false && country.charAt(i) != ' ' )
            return false;
      
      return true;
   }
   
   // Validates a match
   public boolean checkMatch( String idPlayer1, String idPlayer2, int year, int month, int day, String tournament, String score )
   {
      // Check that players are not the same
      if ( idPlayer1.equals(idPlayer2) == true )
         return false;
      
      // Check that match is not in the future
      if ( year > 2020 || year < 0 )
         return false;
      if ( month > 12 || month < 0 )
         return false;
      if ( day > 31 || day < 0 )
         return false;
      
      // Check that tornament only consists of letters and spaces
      if ( tournament == null ) // Check if the String is null
         return false;
      length = tournament.length();
      for ( int i = 0; i < length; i++ ) 
         if ( Character.isLetter(tournament.charAt(i)) == false && tournament.charAt(i) != ' ' )
            return false;
      
      // Check that score is valid
      if ( score == null ) // checks if the String is null
         return false;
      length = score.length();
      for ( int i = 0; i < length-1; i++ ) 
         if (( Character.isDigit(score.charAt(i)) == false && score.charAt(i) != '-' && score.charAt(i) != ',' ) 
            || ( score.contains("-") == false ))
            return false; 
         
      return true;
   }
}