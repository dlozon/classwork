----------------------------------------------------------------------------------
-- Company:        Kettering University
-- Engineer:       David Foster
-- 
-- Create Date:    10/29/19 
-- Design Name:    
-- Module Name:    xor_2 - Behavioral 
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

entity xor_2 is
    Port ( a : in   STD_LOGIC;
           b : in   STD_LOGIC;
           y : out  STD_LOGIC);
end xor_2;

architecture Behavioral of xor_2 is

begin
  process(a,b)
  begin
    if ((a = '0') and (b = '1')) or ((a = '1') and (b = '0')) then
      y <= '1';
	else
	  y <= '0';
    end if;
  end process; 
end Behavioral;

