-- Dylan Lozon
-- CE-210 Lab 3

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY Lab3_part4 IS
	PORT(	SW		:IN	STD_LOGIC_VECTOR(3 DOWNTO 0);
			LEDR 	:OUT	STD_LOGIC_VECTOR(9 DOWNTO 0)
		 );
END Lab3_part4;
		 
ARCHITECTURE order OF Lab3_part4 IS
	ALIAS A : STD_LOGIC is SW(3);
	ALIAS B : STD_LOGIC is SW(2);
	ALIAS C : STD_LOGIC is SW(1);
	ALIAS D : STD_LOGIC is SW(0);
	ALIAS F : STD_LOGIC is LEDR(3);
	
BEGIN
	F <= ((not A) or C) and (B or (not D)) and (A or D);
END order;