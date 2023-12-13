-- Dylan Lozon
-- Lab 4 POS

-- Author of Template: Dr. David Foster
-- Purpose: use the DE10-Lite 7-segment LED displays



LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY Lab4_pos IS
   PORT( SW	    :IN  STD_LOGIC_VECTOR(3 DOWNTO 0);
         HEX4   :OUT STD_LOGIC_VECTOR(6 DOWNTO 0)
		 );
END Lab4_pos;

ARCHITECTURE temp OF Lab4_pos IS

   COMPONENT hex_to_7seg  -- update the name
	   PORT( S	  :IN	   STD_LOGIC_VECTOR(3 DOWNTO 0);
            HEX  :OUT   STD_LOGIC_VECTOR(6 DOWNTO 0)
		);
   END COMPONENT;
 
BEGIN
   U0 : hex_to_7seg port map (SW(3 DOWNTO 0), HEX4);
END temp;