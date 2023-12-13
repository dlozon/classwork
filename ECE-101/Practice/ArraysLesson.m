clear

rowVect = [1,2,3,4]; % Create a 1d array
scoreOut = rowVect(1) % Get the first (0th) element in the array
scoreOut2 = rowVect(2) % Get the second element in the array
rowVect(5:7) = [5,6,7] % "append" some elements to the array

rowVect2 = [9,10,11,12] % Create a new 1d array
rowVect2(2) = 13 % Replace the second element

% Create a 1d array containing 11 evenly spaced values between 1 and 10
rowVect3 = linspace(0,10,11)

colVect = [14; 15; 16; 17] % Create a vertical array (still kind of 1d)
colItem = colVect(2) % Get the second column element from the array
colVect = rowVect' % Transpose the first horizontal array to be vertical

% Create a 1d array containing 11 evenly spaced values between 10 and 20
colVect2 = linspace(10,20,11)'