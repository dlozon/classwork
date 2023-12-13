--Dylan Lozon
--CE 210-04L Lab 6a

LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_ARITH.ALL;

ENTITY fulladder_behave is
	PORT ( A : IN  	STD_LOGIC;
          B : IN  	STD_LOGIC;
	     Cin : IN 	STD_LOGIC;
          S : OUT 	STD_LOGIC;
	    Cout : OUT   STD_LOGIC);
END fulladder_behave;

ARCHITECTURE Behavioral OF fulladder_behave IS
BEGIN

	PROCESS(A,B,Cin)
	BEGIN 
  
  
		IF ((A xor B) xor Cin) = '1' THEN
			S <= '1';
		ELSE
			S <= '0';
		END IF;
	 
	 
		IF (((A xor B) and Cin) or (A and B)) = '1' THEN
			Cout <= '1';
		ELSE
			Cout <= '0';
		END IF;
	 
	 
	END PROCESS; 
  
END Behavioral;