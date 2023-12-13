----------------------------------------------------------------------------------
-- Company:        Kettering University
-- Engineer:       David Foster
-- 
-- Create Date:    11/19/19
-- Design Name:    
-- Module Name:    3 to 8 decoder - Behavioral 
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

entity decoder3to8 is
   Port ( d : in   STD_LOGIC_VECTOR(2 downto 0);
          y : out  STD_LOGIC_VECTOR(7 downto 0)
   );
end decoder3to8;

architecture Behavioral of decoder3to8 is

begin
   y(0) <= not d(2) and not d(1) and not d(0);
   y(1) <= not d(2) and not d(1) and     d(0);
   y(2) <= not d(2) and     d(1) and not d(0);
   y(3) <= not d(2) and     d(1) and     d(0);
   y(4) <=     d(2) and not d(1) and not d(0);
   y(5) <=     d(2) and not d(1) and     d(0);
   y(6) <=     d(2) and     d(1) and not d(0);
   y(7) <=     d(2) and     d(1) and     d(0);
end Behavioral;

