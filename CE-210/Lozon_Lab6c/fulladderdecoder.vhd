--Dylan Lozon
--CE 210-04L Lab 6c

LIBRARY ieee;
USE ieee.std_logic_1164.all; 

ENTITY fulladderdecoder IS 
	PORT
	(SW :  IN  STD_LOGIC_VECTOR(9 DOWNTO 7);
	 LEDR :  OUT  STD_LOGIC_VECTOR(9 DOWNTO 8));
END fulladderdecoder;

ARCHITECTURE Behavioral OF fulladderdecoder IS 

	COMPONENT decoder3to8
		PORT(d : IN STD_LOGIC_VECTOR(2 DOWNTO 0);
			  y : OUT STD_LOGIC_VECTOR(7 DOWNTO 0)
		);
	END COMPONENT;

	SIGNAL	d :  STD_LOGIC_VECTOR(2 DOWNTO 0);
	SIGNAL	y :  STD_LOGIC_VECTOR(7 DOWNTO 0);

BEGIN 

U0 : decoder3to8 PORT MAP(d => d, y => y);

d(0) <= SW(7); --Input Cin
d(1) <= SW(8); --Input B
d(2) <= SW(9); --Input A

LEDR(8) <= y(1) OR y(4) OR y(7) OR y(2); --Output S

LEDR(9) <= y(3) OR y(6) OR y(7) OR y(5); --Output Cout

END behavioral;