% Author:     Dylan Lozon
% Class:      ECE 101
% Assignment: Exercise 1
% Date:       Apr 11 2023 

% Purpose: This program determines if a robot is too close to
% an obstacle by reading from an ultrasonic sensor

%% Prepare working environment
clear % Clear workspace values
clc % Clear console

%% Declare and initialize variables
% Although measurments are expected in mm, any unit will 
% work as long as the unit is consistent
sensorReading = 80; % Measured in mm
minDistance = 40; % measured in mm

% You may uncomment the following line if you wish to 
% take input from the commandline. This is useful for testing.
%sensorReading = input("What is the current reading from the sensor?\n > ");

%% Determine if the robot is too close to an obstacle
tooClose = sensorReading < minDistance;

%% Execute some code based on whether the robot is a safe distance away
if (tooClose)
    fprintf("The robot is too close to an obstacle, and should move away.\n");
else
    fprintf("The robot has enough space to move forward.\n");
end