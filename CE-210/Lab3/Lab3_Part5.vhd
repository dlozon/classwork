-- Dylan Lozon
-- CE-210 Lab 3

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY Lab3_part5 IS
	PORT(	SW		:IN	STD_LOGIC_VECTOR(9 DOWNTO 6);
			LEDR    :OUT STD_LOGIC_VECTOR(9 DOWNTO 0)
		 );
END Lab3_part5;
		 
ARCHITECTURE struct OF Lab3_part5 IS
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

	SIGNAL nA,nB,nC,nD, nACD,BCnD,AD: STD_LOGIC;  -- declare internal signal here
	-- Note that STD_LOGIC is used for single bits without an index
	
    ALIAS A : STD_LOGIC is SW(9);
    ALIAS B : STD_LOGIC is SW(8);
    ALIAS C : STD_LOGIC is SW(7);
    ALIAS D : STD_LOGIC is SW(6);
    ALIAS F : STD_LOGIC is LEDR(9);
	
BEGIN
    U0 : inv_1 port map (A,nA);  -- can use just labels if they are all in order
    U1 : inv_1 port map (B,nB); -- can explicitly connect signal to port in any order
    U2 : inv_1 port map (C,nC);
    U3 : inv_1 port map (D,nD);
    U4 : and_3 port map (nA,C,D,nACD);
    U5 : and_3 port map (B,C,nD,BCnD);
    U6 : and_2 port map (A,D,AD);
    U7 : or_3  port map (nACD,BCnD,AD,F);
    
END struct;