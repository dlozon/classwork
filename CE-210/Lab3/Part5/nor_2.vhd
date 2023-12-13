----------------------------------------------------------------------------------
-- Company:        Kettering University
-- Engineer:       David Foster
-- 
-- Create Date:    10/29/19
-- Design Name:    
-- Module Name:    nor_2 - Behavioral 
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
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity nor_2 is
    Port ( a : in   STD_LOGIC;
           b : in   STD_LOGIC;
           y : out  STD_LOGIC);
end nor_2;

architecture Behavioral of nor_2 is

begin
  process(a,b)
  begin
  	if not(a or b) = '1' then
      y <= '1';
  	else
      y <= '0';
  	end if;
  end process; 
end Behavioral;

