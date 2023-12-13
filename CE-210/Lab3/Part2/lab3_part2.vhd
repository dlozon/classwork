-- Author: Dr. David Foster
-- Last Modified: 10/29/2019
-- Purpose: demonstrate concurrent statements

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY Lab3_part2 IS
	PORT(	SW		:IN	STD_LOGIC_VECTOR(3 DOWNTO 0);
			LEDR 	:OUT	STD_LOGIC_VECTOR(9 DOWNTO 0)
		 );
END Lab3_part2;
		 
ARCHITECTURE order OF Lab3_part2 IS
	SIGNAL R,S,T: STD_LOGIC;  -- declare internal signal here
	-- Note that STD_LOGIC is used for single bits without an index
	
	-- More useful names can be given to the input/output ports
	-- so the code is more understandable below.
	ALIAS A : STD_LOGIC is SW(3);
	ALIAS B : STD_LOGIC is SW(2);
	ALIAS C : STD_LOGIC is SW(1);
	ALIAS D : STD_LOGIC is SW(0);
	ALIAS Y : STD_LOGIC is LEDR(7);
	
BEGIN
	Y <= R or S or T;
	T <= (not A) and (not B) and (not D);
	S <= (not C) and D;
	R <= A and B and C;
	
	-- Uncomment for Question 4
	--Y <= A and B and C or (not C) and D or (not A) and (not B) and (not D);
	

-- Because the other LEDs are not used, depending on what was previously programmed,
-- the unsused LEDs sometimes show as dim;y lit instead of off.
-- Uncomment the following two lines to force the other LEDs to off if that happens...and it probably a good habit.	
--	LEDR(9 DOWNTO 8) <= "00";
--	LEDR(6 DOWNTO 0) <= "0000000";
END order;