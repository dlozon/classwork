----------------------------------------------------------------------------------
-- Company:        Kettering University
-- Engineer:       David Foster
-- 
-- Create Date:    10/29/2019
-- Design Name:    
-- Module Name:    and_3 - Behavioral 
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

entity and_3 is
    Port ( a : in  	STD_LOGIC;
           b : in  	STD_LOGIC;
	       c : in  	STD_LOGIC;
           y : out 	STD_LOGIC);
end and_3;

architecture Behavioral of and_3 is

begin
  process(a,b,c)
  begin
    if (a and b and c) = '1' then
      y <= '1';
    else
      y <= '0';
    end if;
  end process; 
end Behavioral;

