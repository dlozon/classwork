-- Dylan Lozon
-- Lab 4 SOP

-- Author of Template: Dr. David Foster
-- Purpose: use the DE10-Lite 7-segment LED displays



LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY Lab4_two_digits IS
   PORT( SW	    :IN  STD_LOGIC_VECTOR(9 DOWNTO 0);
         HEX5   :OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
			HEX4   :OUT STD_LOGIC_VECTOR(6 DOWNTO 0)
		 );
END Lab4_two_digits;

ARCHITECTURE temp OF Lab4_two_digits IS

   COMPONENT hex_to_7seg_sop  -- update the name
	   PORT( S	  :IN	   STD_LOGIC_VECTOR(9 DOWNTO 6);
            HEX  :OUT   STD_LOGIC_VECTOR(6 DOWNTO 0)
		);
   END COMPONENT;
	
	COMPONENT hex_to_7seg_pos  -- update the name
	   PORT( S	  :IN	   STD_LOGIC_VECTOR(3 DOWNTO 0);
            HEX  :OUT   STD_LOGIC_VECTOR(6 DOWNTO 0)
		);
   END COMPONENT;
 
BEGIN
   U0 : hex_to_7seg_sop port map (SW(9 DOWNTO 6), HEX5);
	U1 : hex_to_7seg_pos port map (SW(3 DOWNTO 0), HEX4);
END temp;