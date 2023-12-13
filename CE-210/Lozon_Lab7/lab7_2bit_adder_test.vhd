----------------------------------------------------------------------------------
-- Company:        Kettering University
-- Engineer:       David Foster
-- 
-- Create Date:    9/1/20
-- Design Name:    
-- Module Name:    2-bit adder test
-- Project Name:   
-- Target Devices: DE10-Lite Development board
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

ENTITY lab7_2bit_adder_test IS
   Port ( SW  : in   STD_LOGIC_VECTOR(9 downto 0);
          LEDR: out  STD_LOGIC_VECTOR(9 downto 0)
   );
END lab7_2bit_adder_test;

ARCHITECTURE test OF lab7_2bit_adder_test IS

   COMPONENT lab7_2bit_adder
   Port ( A    : in  STD_LOGIC_VECTOR (1 DOWNTO 0);
          B    : in  STD_LOGIC_VECTOR (1 DOWNTO 0); 
          Cin  : in  STD_LOGIC;
          S    : out STD_LOGIC_VECTOR (1 DOWNTO 0);
          C    : out STD_LOGIC;
          V    : out STD_LOGIC
   );
   END COMPONENT;
   
   SIGNAL Awire, Bwire, Swire : STD_LOGIC_VECTOR(1 downto 0);
   SIGNAL Cbitwire, Vbitwire : STD_LOGIC; 
-- Add components as necessary
   
-- Add aliases as necessary
 
   
BEGIN
   -- connect to the external switches
   Awire(1) <= SW(4);    
   Awire(0) <= SW(3);
   Bwire(1) <= SW(1);    
   Bwire(0) <= SW(0);
   -- monitor the values of the switces visually by connecting them to LEDs
   LEDR(4)   <= Awire(1);
   LEDR(3)   <= Awire(0);
   LEDR(1)   <= Bwire(1);
   LEDR(0)   <= Bwire(0);

   DUT : lab7_2bit_adder port map (Awire, Bwire, '0', Swire, Cbitwire, Vbitwire); 
   
   -- monitor the 2-bit adder's outputs
   LEDR(9) <= Vbitwire;
   LEDR(8) <= Cbitwire;
   LEDR(7) <= Swire(1);
   LEDR(6) <= Swire(0);
     
END test;
          
