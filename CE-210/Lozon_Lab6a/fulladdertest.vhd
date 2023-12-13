----------------------------------------------------------------------------------
-- Company:        Kettering University
-- Engineer:       David Foster
-- 
-- Create Date:    8/20/20
-- Design Name:    
-- Module Name:    test bench for full-adder
-- Project Name:   CE-210 gate 
-- Target Devices: CE10-Lite Development board
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.all;

-- Note that this top-level entity name must be changed to match the name of the project
ENTITY fulladdertest IS   
   PORT ( SW : in std_logic_vector(9 downto 7);
          LEDR : out std_logic_vector(9 downto 8) 
   );
END fulladdertest;

ARCHITECTURE test OF fulladdertest IS

   -- the component name here and on line 47 must be changed to match the name of the different full adder entities,
   -- depending on which one is being tested.
   COMPONENT fulladder_behave
   Port ( A,B,Cin : in   STD_LOGIC;
          S, Cout : out  STD_LOGIC
   );
   END COMPONENT;
   
   ALIAS Ai  : STD_LOGIC is SW(9);
   ALIAS Bi  : STD_LOGIC is SW(8);
   ALIAS Cin : STD_LOGIC is SW(7); 
   ALIAS Si  : STD_LOGIC is LEDR(8);
   ALIAS Cout: STD_LOGIC is LEDR(9);
   
BEGIN

   DUT0 : fulladder_behave port map (Ai,Bi,Cin,Si,Cout);
   
END test;
          
