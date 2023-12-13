--Dylan Lozon
--CE 210-04L Lab 7

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY lab7_2bit_adder IS
   Port ( A,B : in   STD_LOGIC_VECTOR(1 downto 0);
          Cin : in   STD_LOGIC;
          S   : out  STD_LOGIC_VECTOR(1 downto 0);
          C,V : out  STD_LOGIC -- unsigned overflow and signed overflow, respectively
   );
END lab7_2bit_adder;

ARCHITECTURE test OF lab7_2bit_adder IS

   COMPONENT fulladder_behave     -- You may need to change the component name to match your full-adder entity
   Port ( A,B,Cin : in   STD_LOGIC;
          S, Cout : out  STD_LOGIC
   );
   END COMPONENT;
   
SIGNAL	Cout_WIRE_0 :  STD_LOGIC;
SIGNAL	Cout_WIRE_1 :  STD_LOGIC;
   
BEGIN 

	C <= Cout_WIRE_1;

	V <= Cout_WIRE_0 XOR Cout_WIRE_1;

	U0 : fulladder_behave
	PORT MAP(A => A(0),
				B => B(0),
				Cin => '0',
				S => S(0),
				Cout => Cout_WIRE_0);
				
	
	U1 : fulladder_behave
	PORT MAP(A => A(1),
			   B => B(1),
		      Cin => Cout_WIRE_0,
				S => S(1),
				Cout => Cout_WIRE_1);
				
END test;
          
