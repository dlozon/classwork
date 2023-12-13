----------------------------------------------------------------------------------
-- Company:        Kettering University
-- Engineer:       David Foster
-- 
-- Create Date:    11/19/19
-- Design Name:    
-- Module Name:    4 to 1 mux - Behavioral 
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

entity mux4to1 is
    Port ( d : in   STD_LOGIC_VECTOR(3 downto 0);
           s : in   STD_LOGIC_VECTOR(1 downto 0);
           y : out  STD_LOGIC);
end mux4to1;

architecture Behavioral of mux4to1 is

begin
   process(d,s)
   begin
      case s is
      when "00" => y <= d(0);
      when "01" => y <= d(1);
      when "10" => y <= d(2);
      when "11" => y <= d(3);
      when others => y <= 'Z';
   end case;
  end process; 
end Behavioral;

