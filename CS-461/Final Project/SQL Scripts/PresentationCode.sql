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


-- Start by creating a district
INSERT INTO `district` (DistrictCode, DistrictName) 
VALUES
	('FIT', 'FIRST In Texas Districk'),
	('FIN', 'FIRST Indiana Robotics District'),
	('FOM', 'FIRST In Michigan District');

-- You can then populate the districts with events
INSERT INTO `event` (EventCode, EventName, DistrictCode, Location, StartDate, EndDate)
VALUES
    ('MIKEN', 'FIM District Kentwood Event presented by Dematic', 'FOM', 'Kentwood, Michigan', '2023-03-08', '2023-03-10'),
    ('MILA2', 'FIM District Lakeview Event #2', 'FOM', 'Lakeview, Michigan', '2023-03-22', '2023-03-24'),
    ('MIDET', 'FIM District Detroit Event presented by DTE', 'FOM', 'Detroit, Michigan', '2023-03-01', '2023-03-03'),
    
-- However, an event can also be districtless
	('BIGBANG', 'BIG BANG Offseason Event Hosted by Team 280', NULL, 'Taylor, Michigan', '2023-06-30', '2023-07-01' );
    
-- oh no! We made mistakes while entering FIM! Fortunately we can just UPDATE its code, and it will CASCADE to the events
UPDATE `district` 
SET `DistrictCode` = 'FIM'
WHERE (`DistrictCode` = 'FOM');

-- However, do not try to delete and remake it. Because has already scheduled events, it is RESTRICTed from deletion
-- DELETE FROM `district` WHERE (`DistrictCode` = 'FIM');

-- FIT, on the other hand, has no events, and can be deleted safely
DELETE FROM `district` WHERE (`DistrictCode` = 'FIT');
    
-- Once there are both districts and events, it's a perfect time to create some teams to play in them
-- NOTE: Teams do not need a district to exist, nor do they need to attend any events
INSERT INTO `team` (TeamNumber, TeamName, SchoolName, RookieYear, DistrictCode)
VALUES
    (33, 'Killer Bees', 'Hive High School', 2000, 'FIM'),
    (5090, 'Torquenados', 'Gear Academy', 2014, 'FIM'),
	(135, 'Penn Robotics', 'Innovation Institute', 1996, 'FIN'),
    (3538, 'RoboJackets', 'Tech Center', 2011, 'FIM'),
    (254, 'Cheesy Poofs', 'Innovation High School', 1999, NULL),
    (27, 'Team Rush', 'Velocity High School', 1997, 'FIM');
    
-- Of course, each team will need a robot in order to compete
-- NOTE: teams are technically able to compete without a robot to account for the case that their robot has simply not been documented
-- NOTE conversely, a robot cannot exist without being associated with a team
INSERT INTO `robot` (TeamNumber, RobotName, WeightKG, LengthCM, HeightCM, WidthCM)
VALUES
    (33, 'BumbleBot', 50, 70, 120, 60),
    (5090, 'TorqueMaster', 45, 65, 110, 55),
    (254, 'CheesyBot', 60, 80, 140, 70),
    (27, 'RushBot', 48, 68, 115, 58),
    (135, 'PennBot', 51, 71, 122, 61),
    (3538, 'RoboSting', 56, 76, 132, 66);
    
-- The teams can attend events by being added to the attends table
-- We're going to assume that every team has decided to go to MIDET for convenience
INSERT INTO `attends` (TeamNumber, EventCode)
SELECT TeamNumber, 'MIDET'
FROM team;

-- Matches have to be defined before we can say who played in them, so let's declare 5 matches at MIDET
-- Create 5 matches for each of AZVA, FIMID, FLOR, and MIDET
INSERT INTO `match` (MatchNumber, EventCode, RedAllianceScore, BlueAllianceScore)
VALUES
	(1, 'MIDET', 120, 125),
    (2, 'MIDET', 130, 135),
    (3, 'MIDET', 115, 115),
    (4, 'MIDET', 125, 130),
    (5, 'MIDET', 135, 140);

-- We now use the plays_in relationship to say which teams are playing in which match
-- Let's fill in match 1 at MIDET
INSERT INTO plays_in (TeamNumber, MatchNumber, EventCode, AllianceColor)
VALUES
    (33, 1, 'MIDET', 'RED'),
    (5090, 1, 'MIDET', 'RED'),
    (135, 1, 'MIDET', 'RED'),
    (3538, 1, 'MIDET', 'BLUE'),
    (254, 1, 'MIDET', 'BLUE');
    
-- There are 3 main constraints here

-- 1. A team cannot play in the same match twice
-- (33, 1, 'MIDET', 'BLUE');

-- 2. There can only be three teams per alliance
-- UPDATE `plays_in` SET `AllianceColor` = 'RED' WHERE (`TeamNumber` = '254') AND (`MatchNumber` = '1') AND (`EventCode` = 'MIDET');

-- 3. A team must be attending an event to play a match at that event.
-- DELETE FROM `attends` WHERE (`TeamNumber` = '27') and (`EventCode` = 'MIDET');
-- INSERT INTO plays_in (TeamNumber, MatchNumber, EventCode, AllianceColor) VALUE (27, 1, 'MIDET', 'BLUE');

