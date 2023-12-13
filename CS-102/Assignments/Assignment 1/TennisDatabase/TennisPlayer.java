// TennisPlayer object

package TennisDatabase;

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
   // Returns -1 if it comes after its comparison, 1 if before, and 0 if they have the same id
   public int compareTo( TennisPlayer o ) 
   {
      String id = o.getId();
      String newId = getId();
      
      if ( id.compareTo(newId) < 0 )
         return -1;
      else if ( id.compareTo(newId) == 0 )
         return 0;
      else
          return 1;
   }
   
   public void displayAsString()
   {
      System.out.println( "PLAYER/" + this.id + "/" + this.firstName + "/" + this.lastName + "/" + this.birthYear + "/" + this.country );
   }
}