-- Create the database
CREATE DATABASE IF NOT EXISTS `robotics_database`;

-- Create the district table
CREATE TABLE IF NOT EXISTS `robotics_database`.`district` (
  `DistrictCode` varchar(3) NOT NULL,
  `DistrictName` varchar(255) NOT NULL,
  PRIMARY KEY (`DistrictCode`)
);

-- Create the event table
-- Must happen after district creation
CREATE TABLE IF NOT EXISTS `robotics_database`.`event` (
  `EventCode` varchar(10) NOT NULL,
  `EventName` varchar(255) NOT NULL,
  `Location` varchar(255) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `DistrictCode` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`EventCode`),
  KEY `EventDistrict` (`DistrictCode`),
  CONSTRAINT `EventDistrict` FOREIGN KEY (`DistrictCode`) REFERENCES `district` (`DistrictCode`) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Create the team table
-- Must happen after district creation
CREATE TABLE IF NOT EXISTS `robotics_database`.`team` (
  `TeamNumber` int NOT NULL,
  `TeamName` varchar(255) NOT NULL,
  `SchoolName` varchar(255) NOT NULL,
  `RookieYear` int NOT NULL,
  `DistrictCode` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`TeamNumber`),
  KEY `TeamDistrict_idx` (`DistrictCode`),
  CONSTRAINT `TeamDistrict` FOREIGN KEY (`DistrictCode`) REFERENCES `district` (`DistrictCode`) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Create the robot table
-- Must happen after team creation
CREATE TABLE IF NOT EXISTS `robotics_database`.`robot` (
  `TeamNumber` int NOT NULL,
  `RobotName` varchar(45) DEFAULT NULL,
  `WeightKG` float NOT NULL,
  `LengthCM` float NOT NULL,
  `HeightCM` float NOT NULL,
  `WidthCM` float NOT NULL,
  PRIMARY KEY (`TeamNumber`),
  CONSTRAINT `RobotTeam` FOREIGN KEY (`TeamNumber`) REFERENCES `team` (`TeamNumber`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create the attends table
-- Must happen after both team and event tables have been created
CREATE TABLE IF NOT EXISTS `robotics_database`.`attends` (
  `TeamNumber` int NOT NULL,
  `EventCode` varchar(10) NOT NULL,
  PRIMARY KEY (`TeamNumber`,`EventCode`),
  KEY `EventAttended_idx` (`EventCode`),
  CONSTRAINT `EventAttended` FOREIGN KEY (`EventCode`) REFERENCES `event` (`EventCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TeamAttending` FOREIGN KEY (`TeamNumber`) REFERENCES `team` (`TeamNumber`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create the match table
-- Must happen after event creation
CREATE TABLE IF NOT EXISTS `robotics_database`.`match` (
  `MatchNumber` int NOT NULL,
  `EventCode` varchar(10) NOT NULL,
  `RedAllianceScore` int NOT NULL,
  `BlueAllianceScore` int NOT NULL,
  `WinningAlliance` enum('RED','BLUE','TIE') GENERATED ALWAYS AS ((case when (`RedAllianceScore` > `BlueAllianceScore`) then _utf8mb4'RED' when (`BlueAllianceScore` > `RedAllianceScore`) then _utf8mb4'BLUE' else _utf8mb4'TIE' end)) STORED,
  PRIMARY KEY (`MatchNumber`,`EventCode`),
  KEY `MatchEvent` (`EventCode`),
  CONSTRAINT `MatchEvent` FOREIGN KEY (`EventCode`) REFERENCES `event` (`EventCode`)
);

-- Create the plays_in table
-- Must happen after all of team, match, and event tables have been created
CREATE TABLE IF NOT EXISTS `robotics_database`.`plays_in` (
  `TeamNumber` int NOT NULL,
  `MatchNumber` int NOT NULL,
  `EventCode` varchar(10) NOT NULL,
  `AllianceColor` enum('RED','BLUE') NOT NULL,
  PRIMARY KEY (`TeamNumber`,`MatchNumber`,`EventCode`),
  KEY `Match` (`MatchNumber`,`EventCode`),
  CONSTRAINT `MatchPlayed` FOREIGN KEY (`MatchNumber`, `EventCode`) REFERENCES `match` (`MatchNumber`, `EventCode`),
  CONSTRAINT `TeamPlaying` FOREIGN KEY (`TeamNumber`) REFERENCES `team` (`TeamNumber`) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Add a trigger to validate insertions into the plays_in table
DELIMITER //
CREATE TRIGGER `validateInsertion` BEFORE INSERT ON `robotics_database`.`plays_in` FOR EACH ROW
BEGIN
  DECLARE teamsOnAlliance INT;
  DECLARE teamIsAtEvent INT;

-- Check if the team is attending the event this match is being played at
  SELECT COUNT(*) INTO teamIsAtEvent
  FROM `attends`
  WHERE `TeamNumber` = NEW.`TeamNumber`
    AND `EventCode` = NEW.`EventCode`;

  -- Check if the team is attending the event
  IF teamIsAtEvent = 0 THEN
    -- Raise an error
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The team is not attending the specified event, so cannot play matches held there.';
  END IF;

  -- Count how many teams are already on the alliance you are trying to join
  SELECT COUNT(*) INTO teamsOnAlliance
  FROM `plays_in`
  WHERE `MatchNumber` = NEW.`MatchNumber`
    AND `EventCode` = NEW.`EventCode`
    AND `AllianceColor` = NEW.`AllianceColor`;

  -- Check if there are already three teams
  IF teamsOnAlliance >= 3 THEN
    -- Raise an error
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Only three teams are allowed per AllianceColor in each match.';
  END IF;
END//
-- Run the same trigger before an update
DELIMITER //
CREATE TRIGGER `validateUpdate` BEFORE UPDATE ON `robotics_database`.`plays_in` FOR EACH ROW
BEGIN
  DECLARE teamsOnAlliance INT;
  DECLARE teamIsAtEvent INT;

-- Check if the team is attending the event this match is being played at
  SELECT COUNT(*) INTO teamIsAtEvent
  FROM `attends`
  WHERE `TeamNumber` = NEW.`TeamNumber`
    AND `EventCode` = NEW.`EventCode`;

  -- Check if the team is attending the event
  IF teamIsAtEvent = 0 THEN
    -- Raise an error
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The team is not attending the specified event, so cannot play matches held there.';
  END IF;

  -- Count how many teams are already on the alliance you are trying to join
  SELECT COUNT(*) INTO teamsOnAlliance
  FROM `plays_in`
  WHERE `MatchNumber` = NEW.`MatchNumber`
    AND `EventCode` = NEW.`EventCode`
    AND `AllianceColor` = NEW.`AllianceColor`;

  -- Check if there are already three teams
  IF teamsOnAlliance >= 3 THEN
    -- Raise an error
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Only three teams are allowed per AllianceColor in each match.';
  END IF;
END//