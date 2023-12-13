-- Fill all tables with dummy data

-- These are all of the real districts
INSERT INTO `district` (DistrictCode, DistrictName)
VALUES
    ('FIN', 'FIRST Indiana Robotics District'),
    ('FMA', 'FIRST Mid-Atlantic District'),
    ('PCH', 'Peachtree District'),
    ('FIM', 'FIRST In Michigan District'),
    ('FNC', 'FIRST North Carolina District'),
    ('FIT', 'FIRST In Texas District'),
    ('ISR', 'FIRST Israel District'),
    ('NE', 'New England District'),
    ('ONT', 'Ontario District'),
    ('PNW', 'Pacific Northwest District'),
    ('CHS', 'FIRST Chesapeake District');
    
    
-- These are 25 randomly selected real events with dummy dates
INSERT INTO `event` (EventCode, EventName, DistrictCode, Location, StartDate, EndDate)
VALUES
    ('MITRY', 'FIM District Troy Event #1 presented by Aptiv', 'FIM', 'Troy, Michigan', '2023-07-01', '2023-07-03'),
    ('FLOR', 'Orlando Regional', 'FNC', 'Orlando, Florida', '2023-03-15', '2023-03-17'),
    ('GACMP', 'Peachtree District Championship', 'PCH', 'Atlanta, Georgia', '2023-04-05', '2023-04-07'),
    ('NYLI', 'FIRST Long Island Regional #1', 'FMA', 'Hempstead, New York', '2023-03-08', '2023-03-10'),
    ('CASJ', 'Silicon Valley Regional', 'FNC', 'San Jose, California', '2023-03-22', '2023-03-24'),
    ('WAAHS', 'PNW District Auburn Event', 'PNW', 'Auburn, Washington', '2023-03-01', '2023-03-03'),
    ('FIMID', 'FIM District Midland Event presented by Dow', 'FIM', 'Midland, Michigan', '2023-03-15', '2023-03-17'),
    ('VABLA', 'CHS District Blacksburg VA Event', 'CHS', 'Blacksburg, Virginia', '2023-03-29', '2023-03-31'),
    ('AZVA', 'Arizona East Regional', 'FNC', 'Flagstaff, Arizona', '2023-03-08', '2023-03-10'),
    ('GADAL', 'PCH District Dalton Event', 'PCH', 'Dalton, Georgia', '2023-03-15', '2023-03-17'),
    ('MSLR', 'Magnolia Regional', 'FNC', 'Southaven, Mississippi', '2023-03-01', '2023-03-03'),
    ('FLTA', 'Tallahassee Regional', 'FNC', 'Tallahassee, Florida', '2023-03-08', '2023-03-10'),
    ('FLWP', 'South Florida Regional', 'FNC', 'West Palm Beach, Florida', '2023-03-15', '2023-03-17'),
    ('GACAR', 'PCH District Carrollton Event', 'PCH', 'Carrollton, Georgia', '2023-03-22', '2023-03-24'),
    ('MIKEN', 'FIM District Kentwood Event presented by Dematic', 'FIM', 'Kentwood, Michigan', '2023-03-08', '2023-03-10'),
    ('TXHOU', 'FIT District Houston Event', 'FIT', 'Houston, Texas', '2023-03-22', '2023-03-24'),
    ('MNDU', 'Lake Superior Regional', 'FNC', 'Duluth, Minnesota', '2023-03-01', '2023-03-03'),
    ('FMAHAT', 'FMA District Hatboro-Horsham Event', 'FMA', 'Horsham, Pennsylvania', '2023-03-15', '2023-03-17'),
    ('MIRIC', 'FIM District Lakeview Event #1 presented by Parker-Hannifin', 'FIM', 'Lakeview, Michigan', '2023-03-01', '2023-03-03'),
    ('OHCL', 'Buckeye Regional', 'FNC', 'Cleveland, Ohio', '2023-03-08', '2023-03-10'),
    ('ONWAT', 'ONT District University of Waterloo Event', 'ONT', 'Waterloo, Ontario', '2023-03-15', '2023-03-17'),
    ('MILA2', 'FIM District Lakeview Event #2', 'FIM', 'Lakeview, Michigan', '2023-03-22', '2023-03-24'),
    ('NYLI2', 'FIRST Long Island Regional #2', 'FMA', 'Hempstead, New York', '2023-03-15', '2023-03-17'),
    ('MIDET', 'FIM District Detroit Event presented by DTE', 'FIM', 'Detroit, Michigan', '2023-03-01', '2023-03-03'),
    ('MOSE', 'Central Missouri Regional', 'FNC', 'Lee\'s Summit, Missouri', '2023-03-22', '2023-03-24'),
    ('TNKN', 'Smoky Mountains Regional', 'FNC', 'Knoxville, Tennessee', '2023-03-15', '2023-03-17');    
    
    
-- These are a bunch of real team names and numbers I threw in with dummy school names and rookie years
INSERT INTO `team` (TeamNumber, TeamName, SchoolName, RookieYear, DistrictCode)
VALUES
    (33, 'Killer Bees', 'Hive High School', 2000, 'FIM'),
    (5090, 'Torquenados', 'Gear Academy', 2014, 'FIM'),
    (548, 'Robostangs', 'Tech Institute', 2001, 'FIM'),
    (254, 'Cheesy Poofs', 'Innovation High School', 1999, NULL),
    (27, 'Team Rush', 'Velocity High School', 1997, 'FIM'),
    (1690, 'Orbit', 'Galaxy Academy', 2011, 'ISR'),
    (46, 'Space Monkeys', 'Astro High School', 1996, 'ISR'),
    (2056, 'OP Robotics', 'Automation Institute', 2006, 'ONT'),
    (3683, 'Team DAVE', 'RoboTech School', 2012, 'ONT'),
    (292, 'PantherTech', 'Techville High School', 1998, 'FIN'),
    (135, 'Penn Robotics', 'Innovation Institute', 1996, 'FIN'),
    (3538, 'RoboJackets', 'Tech Center', 2011, 'FIM'),
    (2337, 'EngiNERDs', 'Inventor High School', 2008, 'FIM'),
    (3604, 'Goon Squad', 'Tech Academy', 2011, 'FIM'),
    (1414, 'IHOT', 'Hot Robotics School', 2004, 'PCH'),
    (1746, 'OTTO', 'RoboTech Academy', 2006, 'PCH');
    

-- AI generated dummy data
INSERT INTO `robot` (TeamNumber, RobotName, WeightKG, LengthCM, HeightCM, WidthCM)
VALUES
    (33, 'BumbleBot', 50, 70, 120, 60),
    (5090, 'TorqueMaster', 45, 65, 110, 55),
    (548, 'StangBot', 55, 75, 130, 65),
    (254, 'CheesyBot', 60, 80, 140, 70),
    (27, 'RushBot', 48, 68, 115, 58),
    (1690, 'Orbiter', 52, 72, 125, 62),
    (46, 'SpaceBot', 58, 78, 135, 68),
    (2056, 'OPBot', 47, 67, 112, 56),
    (3683, 'DaveBot', 53, 73, 127, 63),
    (292, 'PantherBot', 49, 69, 118, 59),
    (135, 'PennBot', 51, 71, 122, 61),
    (3538, 'JacketBot', 56, 76, 132, 66),
    (2337, 'NerdBot', 54, 74, 128, 64),
    (3604, 'GoonBot', 46, 66, 113, 57),
    (1414, 'IhotBot', 57, 77, 134, 67),
    (1746, 'OttoBot', 59, 79, 138, 69);
    
    
-- Just make every team go to MIDET and FLOR because I'm lazy
INSERT INTO `attends` (TeamNumber, EventCode)
SELECT TeamNumber, 'MIDET'
FROM team;
INSERT INTO `attends` (TeamNumber, EventCode)
SELECT TeamNumber, 'FLOR'
FROM team;
-- Only have some teams go to AZVA
INSERT INTO `attends` (TeamNumber, EventCode)
SELECT TeamNumber, 'AZVA'
FROM team WHERE TeamNumber > 1000;

-- Create 5 matches for each of AZVA, FIMID, FLOR, and MIDET
INSERT INTO `match` (MatchNumber, EventCode, RedAllianceScore, BlueAllianceScore)
VALUES
    (1, 'AZVA', 100, 90),
    (2, 'AZVA', 95, 95),
    (3, 'AZVA', 105, 100),
    (4, 'AZVA', 95, 85),
    (5, 'AZVA', 155, 100),

    (1, 'FIMID', 150, 140),
    (2, 'FIMID', 155, 150),
    (3, 'FIMID', 145, 140),
    (4, 'FIMID', 160, 145),
    (5, 'FIMID', 165, 160),
    
    (1, 'FLOR', 95, 95),
    (2, 'FLOR', 85, 90),
    (3, 'FLOR', 95, 100),
    (4, 'FLOR', 100, 105),
    (5, 'FLOR', 105, 110),
    
    (1, 'MIDET', 120, 125),
    (2, 'MIDET', 130, 135),
    (3, 'MIDET', 115, 115),
    (4, 'MIDET', 125, 130),
    (5, 'MIDET', 135, 140);
    
-- Fill up 5 matches for MIDET
INSERT INTO plays_in (TeamNumber, MatchNumber, EventCode, AllianceColor)
VALUES
    (27, 1, 'MIDET', 'RED'),
    (33, 1, 'MIDET', 'RED'),
    (46, 1, 'MIDET', 'RED'),
    (135, 1, 'MIDET', 'BLUE'),
    (254, 1, 'MIDET', 'BLUE'),
    (292, 1, 'MIDET', 'BLUE'),
    
    (254, 2, 'MIDET', 'RED'),
    (292, 2, 'MIDET', 'RED'),
    (548, 2, 'MIDET', 'RED'),
    (1414, 2, 'MIDET', 'BLUE'),
    (1690, 2, 'MIDET', 'BLUE'),
    (1746, 2, 'MIDET', 'BLUE'),
    
    (2056, 3, 'MIDET', 'RED'),
    (2337, 3, 'MIDET', 'RED'),
    (3538, 3, 'MIDET', 'RED'),
    (3604, 3, 'MIDET', 'BLUE'),
    (3683, 3, 'MIDET', 'BLUE'),
    (5090, 3, 'MIDET', 'BLUE'),

    (2337, 4, 'MIDET', 'RED'),
    (292, 4, 'MIDET', 'RED'),
    (1746, 4, 'MIDET', 'RED'),
    (1414, 4, 'MIDET', 'BLUE'),
    (3538, 4, 'MIDET', 'BLUE'),
    (5090, 4, 'MIDET', 'BLUE'),
    
    (292, 5, 'MIDET', 'RED'),
    (1690, 5, 'MIDET', 'RED'),
    (3683, 5, 'MIDET', 'RED'),
    (1414, 5, 'MIDET', 'BLUE'),
    (254, 5, 'MIDET', 'BLUE'),
    (1746, 5, 'MIDET', 'BLUE'),
    
    (292, 1, 'FLOR', 'RED'),
	(5090, 1, 'FLOR', 'RED'),
	(135, 1, 'FLOR', 'RED'),
	(1414, 1, 'FLOR', 'BLUE'),
	(33, 1, 'FLOR', 'BLUE'),
	(1746, 1, 'FLOR', 'BLUE'),

	(135, 2, 'FLOR', 'RED'),
	(33, 2, 'FLOR', 'RED'),
	(292, 2, 'FLOR', 'RED'),
	(254, 2, 'FLOR', 'BLUE'),
	(1414, 2, 'FLOR', 'BLUE'),
	(27, 2, 'FLOR', 'BLUE'),

	(5090, 3, 'FLOR', 'RED'),
	(1414, 3, 'FLOR', 'RED'),
	(135, 3, 'FLOR', 'RED'),
	(292, 3, 'FLOR', 'BLUE'),
	(33, 3, 'FLOR', 'BLUE'),
	(1746, 3, 'FLOR', 'BLUE'),

	(292, 4, 'FLOR', 'RED'),
	(33, 4, 'FLOR', 'RED'),
	(135, 4, 'FLOR', 'RED'),
	(5090, 4, 'FLOR', 'BLUE'),
	(1414, 4, 'FLOR', 'BLUE'),
	(1746, 4, 'FLOR', 'BLUE'),

	(292, 5, 'FLOR', 'RED'),
	(135, 5, 'FLOR', 'RED'),
	(5090, 5, 'FLOR', 'RED'),
	(1414, 5, 'FLOR', 'BLUE'),
	(254, 5, 'FLOR', 'BLUE'),
	(27, 5, 'FLOR', 'BLUE');
		
    