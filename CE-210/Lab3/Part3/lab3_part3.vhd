-- Author: Dr. David Foster
-- Last Modified: 7/10/2020
-- Purpose: demonstrate concurrent statements

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY Lab3_part3 IS
	PORT(	SW		:IN	STD_LOGIC_VECTOR(3 DOWNTO 0);
			LEDR    :OUT STD_LOGIC_VECTOR(9 DOWNTO 0)
		 );
END Lab3_part3;
		 
ARCHITECTURE struct OF Lab3_part3 IS
	-- To use another VHDL entity in this file, add a declaration.
	-- Note that is very similar to the entity portion for each item.
    COMPONENT and_3 
    PORT ( a,b,c : in  STD_LOGIC;
           y     : out STD_LOGIC);
    END COMPONENT;

    COMPONENT and_2 
    PORT ( a,b : in  STD_LOGIC;
           y   : out STD_LOGIC);
    END COMPONENT;

    COMPONENT or_3 
    PORT ( a,b,c : in  STD_LOGIC;
           y     : out STD_LOGIC);
    END COMPONENT;
  
    COMPONENT inv_1 
    PORT ( a : in  STD_LOGIC;
           y : out STD_LOGIC);
    END COMPONENT;

	SIGNAL nA,nB,nC,nD, AnBC,nCD,nAnBnD: STD_LOGIC;  -- declare internal signal here
	-- Note that STD_LOGIC is used for single bits without an index
	
	-- More useful names can be given to the input/output ports
	-- so the code is more understandable below.
    ALIAS A : STD_LOGIC is SW(3);
    ALIAS B : STD_LOGIC is SW(2);
    ALIAS C : STD_LOGIC is SW(1);
    ALIAS D : STD_LOGIC is SW(0);
    ALIAS Y : STD_LOGIC is LEDR(9);
	
BEGIN
    U0 : inv_1 port map (A,nA);  -- can use just labels if they are all in order
    U1 : inv_1 port map (y=>nB, a=>B); -- can explicitly connect signal to port in any order
    U2 : inv_1 port map (C,nC);
    U3 : inv_1 port map (D,nD);
    U4 : and_3 port map (A,nB,C,AnBC);
    U5 : and_2 port map (nC, D, nCD);
    U6 : and_3 port map (nA,nB,nD,nAnBnD);
    U7 : or_3  port map (AnBC,nCD,nAnBnD,Y);
	
	-- assign internal signals to LEDs for the truth table
    LEDR(6) <= nA;
    LEDR(5) <= nB;
    LEDR(4) <= nC;
    LEDR(3) <= nD;
    LEDR(2) <= AnBC;
    LEDR(1) <= nCD;    
    LEDR(0) <= nAnBnD;	
    
    -- disable the other outputs for clarity
    LEDR(8 DOWNTO 7) <= "00";
    
END struct;