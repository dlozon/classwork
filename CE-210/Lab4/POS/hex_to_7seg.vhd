-- Add a useful header
-- Change names of the file, entity, and architecture

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY hex_to_7seg IS
   PORT( S   :IN  STD_LOGIC_VECTOR(3 DOWNTO 0);
         HEX :OUT STD_LOGIC_VECTOR(6 DOWNTO 0)
	);
END ENTITY;

ARCHITECTURE CANONICAL_POS of hex_to_7seg IS

   ALIAS S0 : STD_LOGIC is S(3);
   ALIAS S1 : STD_LOGIC is S(2);
   ALIAS S2 : STD_LOGIC is S(1);
   ALIAS S3 : STD_LOGIC is S(0);
   ALIAS a : STD_LOGIC is HEX(0);
   ALIAS b : STD_LOGIC is HEX(1);
   ALIAS c : STD_LOGIC is HEX(2);
   ALIAS d : STD_LOGIC is HEX(3);
   ALIAS e : STD_LOGIC is HEX(4);
   ALIAS f : STD_LOGIC is HEX(5);
   ALIAS g : STD_LOGIC is HEX(6);   

BEGIN
   a <= not( (S0 or S1 or S2 or (not S3)) and (S0 or (not S1) or S2 or S3) and ((not S0) or S1 or (not S2) or (not S3)) and ((not S0) or (not S1) or S2 or (not S3)) );
   b <= not( ((not S1) or (not S2) or S3) and ((not S0) or (not S2) or (not S3)) and ((not S0) or (not S1) or S3) and (S0 or (not S1) or S2 or (not S3)) );
   c <= not( ((not S0) or (not S1) or S3) and ((not S0) or (not S1) or (not S2)) and (S0 or S1 or (not S2) or S3) );
   d <= not( ((not S1) or (not S2) or (not S3)) and (S0 or S1 or S2 or (not S3)) and (S0 or (not S1) or S2 or S3) and ((not S0) or S1 or (not S2) or S3) );
   e <= not( (S0 or (not S3)) and (S1 or S2 or (not S3)) and (S0 or (not S1) or S2) );
   f <= not( (S0 or S1 or (not S3)) and (S0 or S1 or (not S2)) and (S0 or (not S2) or (not S3)) and ((not S0) or (not S1) or S2 or (not S3)) );
	g <= not( (S0 or S1 or S2) and (S0 or (not S1) or (not S2) or (not S3)) and ((not S0) or (not S1) or S2 or S3) );
END CANONICAL_POS;