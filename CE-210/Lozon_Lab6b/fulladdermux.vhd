----------------------------------------------------------------------------------
-- Company:        Kettering University
-- Engineer:       David Foster
-- 
-- Create Date:    3/9/21
-- Design Name:    
-- Module Name:    full-adder based on multiplexors
-- Project Name:   CE-210 gate 
-- Target Devices: 
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

ENTITY fulladdermux IS
   Port ( A,B,Cin : in   STD_LOGIC;
          S, Cout : out  STD_LOGIC);
END fulladdermux;

ARCHITECTURE test OF fulladdermux IS

   COMPONENT mux4to1
   port (d : in   STD_LOGIC_VECTOR(3 downto 0);
         s : in   STD_LOGIC_VECTOR(1 downto 0);
         y : out  STD_LOGIC);
   END COMPONENT;

   COMPONENT mux8to1
   port (d : in   STD_LOGIC_VECTOR(7 downto 0);
         s : in   STD_LOGIC_VECTOR(2 downto 0);
         y : out  STD_LOGIC);
   END COMPONENT;
   
-- Add components as necessary
   
-- Add aliases as necessary
   
BEGIN
   -- Note that d and s for each MUX is a bit vector, so you must supply a bit vector (array) with the 
   -- same length. Recall back in Lab 3 that signals may be concatenated with the & operator.
   S_gate    : mux4to1 port map (  );
   Cout_gate : mux8to1 port map (  );
   
END test;
          
