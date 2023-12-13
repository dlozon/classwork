-- Dylan Lozon
-- CE-210 Lab 3

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY Lab3_part6 IS
	PORT(	SW		:IN	STD_LOGIC_VECTOR(4 DOWNTO 2);
			LEDR 	:OUT	STD_LOGIC_VECTOR(9 DOWNTO 0)
		 );
END Lab3_part6;
		 
ARCHITECTURE order OF Lab3_part6 IS
	ALIAS A : STD_LOGIC is SW(4);
	ALIAS B : STD_LOGIC is SW(3);
	ALIAS C : STD_LOGIC is SW(2);
	ALIAS Y : STD_LOGIC is LEDR(9);
	ALIAS F : STD_LOGIC is LEDR(8);
	
BEGIN
	Y <= ((not A) and (B xor C)) or (A and (B xor (not C)));
	F <= A xor (B xor C);
END order;