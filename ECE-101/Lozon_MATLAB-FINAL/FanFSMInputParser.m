% utility methods to parse user input for the fan's FSM
classdef FanFSMInputParser
    methods
        % return whether the user wants to quit the program
        function quit = isQuitting(~, inputStr)
            if (strcmpi(inputStr, 'q') || strcmpi(inputStr, 'quit') || ...
                strcmpi(inputStr, 'o') || strcmpi(inputStr, 'off') || ...
                strcmpi(inputStr, 'e') || strcmpi(inputStr, 'end'))
                    quit = true;
            else
                quit = false;
            end
        end

        % return whether the user wants to decrease the fan speed
        function decrease = isDecreasing(~, inputStr)
            if (strcmpi(inputStr, 'd') || strcmpi(inputStr, 'decrease') || ...
                strcmpi(inputStr, 'down') || strcmpi(inputStr, 'l') || ...
                strcmpi(inputStr, 'lower') || strcmpi(inputStr, 'less'))
                    decrease = true;
            else
                decrease = false;
            end
        end

        % return whether the user wants to increase the fan speed
        function increase = isIncreasing(~, inputStr)
            if (strcmpi(inputStr, 'i') || strcmpi(inputStr, 'increase') || ...
                strcmpi(inputStr, 'u') || strcmpi(inputStr, 'up') || ...
                strcmpi(inputStr, 'h') || strcmpi(inputStr, 'higher'))
                    increase = true;
            else
                increase = false;
            end
        end
    end
end