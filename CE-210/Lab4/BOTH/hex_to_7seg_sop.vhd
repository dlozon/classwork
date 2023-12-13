-- Dylan Lozon
-- Lab 4 SOP

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY hex_to_7seg_sop IS
   PORT( S   :IN  STD_LOGIC_VECTOR(3 DOWNTO 0);
         HEX :OUT STD_LOGIC_VECTOR(6 DOWNTO 0)
	);
END ENTITY;

ARCHITECTURE CANONICAL_SOP of hex_to_7seg_sop IS

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
   a <= not( ((not S1) and (not S3)) or (S1 and S2) or ((not S0) and S2) or (S0 and (not S3)) or ((not S0) and S1 and S3) or (S0 and (not S1) and (not S2)) );
   b <= not( ((not S0) and (not S1)) or ((not S1) and (not S2)) or ((not S1) and (not S3)) or ((not S0) and (not S2) and (not S3)) or ((not S0) and S2 and S3) or (S0 and (not S2) and S3) );
   c <= not( ((not S0) and (not S2)) or (S0 and (not s1)) or ((not S0) and S3) or ((not S2) and S3) or ((not S0) and S1) );
   d <= not( (S0 and (not S2)) or ((not S0) and (not S1) and (not S3)) or ((not S1) and S2 and S3) or (S1 and S2 and (not S3)) or (S1 and (not S2) and S3) );
   e <= not( ((not S1) and (not S3)) or (S0 and S1) or (S2 and (not S3)) or (S0 and S2) );
   f <= not( ((not S2) and (not S3)) or (S0 and S2) or (S1 and (not S3)) or (S0 and (not S1)) or ((not S0) and S1 and (not S2)) );
   g <= not( ((not S1) and S2) or (S2 and (not S3)) or (S0 and S3) or (S0 and (not S1)) or ((not S0) and S1 and (not S2)) );
END CANONICAL_SOP;