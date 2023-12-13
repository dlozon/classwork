----------------------------------------------------------------------------------
-- Company:        Kettering University
-- Engineer:       David Foster
-- 
-- Create Date:    10/29/19 
-- Design Name:    
-- Module Name:    and_4 - Behavioral 
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

entity and_4 is
    Port ( a : in   STD_LOGIC;
           b : in   STD_LOGIC;
	       c : in   STD_LOGIC;
           d : in   STD_LOGIC;
           y : out  STD_LOGIC);
end and_4;

architecture Behavioral of and_4 is

begin
  process(a,b,c,d)
  begin
    if (a and b and c and d) = '1' then
      y <= '1';
    else
      y <= '0';
    end if;
  end process; 
end Behavioral;

