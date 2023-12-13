-- Disable SQL_SAFE_UPDATES
SET SQL_SAFE_UPDATES = 0;

-- Delete all data
DELETE FROM `plays_in`;
DELETE FROM `attends`;
DELETE FROM `match`;
DELETE FROM `robot`;
DELETE FROM `team`;
DELETE FROM `event`;
DELETE FROM `district`;

-- Enable SQL_SAFE_UPDATES
SET SQL_SAFE_UPDATES = 1;