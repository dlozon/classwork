-- Return all tie matches
SELECT *
FROM `match` AS m
WHERE m.WinningAlliance = 'TIE';


-- Return all teams attending an event
SELECT *
FROM `team` AS t
JOIN attends AS a ON t.TeamNumber = a.TeamNumber
WHERE a.EventCode = 'AZVA';


-- Return all events attended by a team
SELECT e.*
FROM `event` AS e
JOIN attends AS a ON e.EventCode = a.EventCode
WHERE a.TeamNumber = 5090;


-- Return all teams in a district
SELECT *
FROM `team` as t
WHERE t.DistrictCode = 'FIN';


-- Return all robots named something-bot
SELECT * FROM robot WHERE RobotName LIKE '%bot';


-- Return all teams not in a district
SELECT * FROM team WHERE DistrictCode NOT IN ('FIM');


-- Return all teams not in any district
SELECT * FROM team WHERE DistrictCode IS NULL;


-- Return 10 newest teams
SELECT * FROM team ORDER BY RookieYear DESC LIMIT 10;


-- Return all matches won by a team
SELECT m.*
FROM `match` AS m
JOIN plays_in AS p ON m.MatchNumber = p.MatchNumber AND m.EventCode = p.EventCode
WHERE p.TeamNumber = 254 AND m.WinningAlliance = p.AllianceColor; 


-- Return all matches with a point total of at least 250
SELECT *, m.BlueAllianceScore + m.RedAllianceScore AS TotalScore
FROM `match` AS m
WHERE m.BlueAllianceScore + m.RedAllianceScore >= 250;


-- Return all matches where at least one alliance scored more than 150
SELECT m.MatchNumber, m.EventCode, m.RedAllianceScore, m.BlueAllianceScore
FROM `match` AS m
WHERE m.RedAllianceScore > 150 OR m.BlueAllianceScore > 150;


-- Return the match with the highest score
SELECT *, (RedAllianceScore + BlueAllianceScore) AS totalScore 
FROM `match`
WHERE (RedAllianceScore + BlueAllianceScore) = 
	(SELECT MAX(RedAllianceScore + BlueAllianceScore) FROM `match` );
  
  
-- Return the oldest team(s) in a district
SELECT * FROM team WHERE RookieYear < ALL
	(SELECT RookieYear FROM team WHERE DistrictCode = 'FIM');
  
  
-- Return the lightest robot
SELECT * FROM `robot` WHERE WeightKg = 
	(SELECT MIN(WeightKG) FROM `robot`);
  
  
  -- Return all teams who have attended an event in detroit
  SELECT * FROM team WHERE TeamNumber IN
	(SELECT TeamNumber FROM attends WHERE EventCode IN
		(SELECT EventCode FROM event WHERE Location = 'Detroit, Michigan'));
  
  
-- Create a view to return the events ordered by point total across all their matches
CREATE VIEW EventsByScoreTotal AS
SELECT e.EventCode, e.EventName, SUM(m.RedAllianceScore + m.BlueAllianceScore) AS TotalScore
FROM event AS e
JOIN `match` AS m ON e.EventCode = m.EventCode
GROUP BY e.EventCode, e.EventName
ORDER BY TotalScore DESC;


-- Create a view to return the districts ordered by how many teams they have
CREATE VIEW DistrictsByTeamCount AS
SELECT d.DistrictCode, d.DistrictName, COUNT(t.TeamNumber) AS TeamCount
FROM `district` AS d
JOIN `team` AS t ON d.DistrictCode = t.DistrictCode
GROUP BY d.DistrictCode, d.DistrictName
ORDER BY TeamCount DESC;


-- Return the heaviest robot playing in a given match
SELECT * FROM `robot` WHERE WeightKG = 
	(SELECT MAX(r.WeightKG)
    FROM `plays_in` AS p
	JOIN `robot` AS r ON p.TeamNumber = r.TeamNumber
    WHERE p.MatchNumber = 3 AND p.EventCode = 'MIDET');


-- Return the team with the most wins overall
SELECT t.TeamNumber, t.TeamName, COUNT(*) AS WinCount
FROM `team` AS t
JOIN `plays_in` AS p ON t.TeamNumber = p.TeamNumber
JOIN `match` AS m ON p.MatchNumber = m.MatchNumber AND p.EventCode = m.EventCode
WHERE p.AllianceColor = m.WinningAlliance
GROUP BY t.TeamNumber, t.TeamName
HAVING COUNT(*) = ( SELECT MAX(WinCount)
    FROM (
        SELECT COUNT(*) AS WinCount
        FROM `plays_in` AS p2
        JOIN `match` AS m2 ON p2.MatchNumber = m2.MatchNumber AND p2.EventCode = m2.EventCode
        WHERE p2.AllianceColor = m2.WinningAlliance
        GROUP BY p2.TeamNumber
    ) AS wins
);