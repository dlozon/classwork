-- Dylan Lozon
-- CE-210 Lab 3
LIBRARY ieee;
USE ieee.std_logic_1164.all;

-- This part declares the interface
ENTITY lab3_part1 IS
	PORT ( SW : IN STD_LOGIC_VECTOR(9 DOWNTO 0);
			 LEDR : OUT STD_LOGIC_VECTOR(9 DOWNTO 0)
	);
END lab3_part1;

-- This defines one possible implementation of a lab3_part1.
ARCHITECTURE simple OF lab3_part1 IS
BEGIN
	LEDR(9 DOWNTO 0) <= SW(8 DOWNTO 0) & SW(9);
END simple;