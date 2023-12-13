-- Dylan Lozon
-- Lab 5


LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY bcd_to_7seg IS
   PORT( S   :IN  STD_LOGIC_VECTOR(3 DOWNTO 0);
         HEX :OUT STD_LOGIC_VECTOR(6 DOWNTO 0)
	);
END ENTITY;

ARCHITECTURE XYZ of bcd_to_7seg IS

   ALIAS S3 : STD_LOGIC is S(3);
   ALIAS S2 : STD_LOGIC is S(2);
   ALIAS S1 : STD_LOGIC is S(1);
   ALIAS S0 : STD_LOGIC is S(0);
   ALIAS a : STD_LOGIC is HEX(0);
   ALIAS b : STD_LOGIC is HEX(1);
   ALIAS c : STD_LOGIC is HEX(2);
   ALIAS d : STD_LOGIC is HEX(3);
   ALIAS e : STD_LOGIC is HEX(4);
   ALIAS f : STD_LOGIC is HEX(5);
   ALIAS g : STD_LOGIC is HEX(6);   

BEGIN
   a <= not( S2 OR S0 OR ((NOT S1) AND (NOT S3)) OR (S1 AND S3) );
   b <= not( (NOT S1) OR ((NOT S2) AND (NOT S3)) OR (S2 AND S3) );
   c <= not( (NOT S2) OR S3 OR S1 );
   d <= not( S0 OR ((NOT S1) AND (NOT S3)) OR ((NOT S1) AND S2) OR (S2 AND (NOT S3)) OR (S1 AND (NOT S2) AND S3) );
   e <= not( ((NOT S1) AND (NOT S3)) OR (S2 AND (NOT S3)) );
   f <= not( S0 OR ((NOT S2) AND (NOT S3)) OR (S1 AND (NOT S2)) OR (S1 AND (NOT S3)) );
   g <= not( S0 OR ((NOT S1) AND S2) OR (S2 AND (NOT S3)) OR (S1 AND (NOT S2)) );
END XYZ;