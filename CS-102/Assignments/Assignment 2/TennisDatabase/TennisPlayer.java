// Dylan Lozon
// CS-102, Fall 2020
// Assignment 2

package TennisDatabase;

import java.util.*;

// TennisPlayer object
public class TennisPlayer implements TennisPlayerInterface
{  
   // Initialize variables
   private final String id;
   private final String firstName;
   private final String lastName;
   private final int birthYear;
   private final String country;
   
   public TennisPlayer ( String id, String firstName, String lastName, int birthYear, String country )
   {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.birthYear = birthYear;
      this.country = country;
   }
   
   // Accessor methods
   public String getId() { return this.id; }
   public String getFirstName() { return this.firstName; }
   public String getLastName() { return this.lastName; }
   public int getBirthYear() { return this.birthYear; }
   public String getCountry() { return this.country; }
   
   // Compares players based on id
   // Returns -1 if it comes alphabetically after its comparison, 1 if before, and 0 if they have the same id
   public int compareTo( TennisPlayer p ) 
   {
      try
      {
         String id = p.getId();
         String newId = getId();
      
         if ( id.compareTo(newId) < 0 )
            return -1;
         else if ( id.compareTo(newId) == 0 )
            return 0;
         else
            return 1;
      }
      catch ( NullPointerException e ) { return 1; }
   }
   
   // Returns a player as a string
   // Used to print players to the console
   // Also used for writing players to a file
   public String toString()
   {
      return ( "PLAYER/" + this.id + "/" + this.firstName + "/" + this.lastName + "/" + this.birthYear + "/" + this.country );
   }
}