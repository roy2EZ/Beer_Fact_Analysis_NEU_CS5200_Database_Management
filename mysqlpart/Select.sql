USE BeerFactAnalysis;

-- 1. Which brewery has the most welcomed beer (highest overall score)?
SELECT BreweryName AS 'Most Welcomed Beer'
FROM Breweries
INNER JOIN (
  SELECT BreweryId, AVG(OverallScore) AS OSCORE
  FROM Beers
  INNER JOIN Overalls ON Overalls.BeerId = Beers.BeerId
  GROUP BY BreweryId
  ORDER BY OSCORE DESC
  LIMIT 1
) AS TEMP
ON TEMP.BreweryId = Breweries.BreweryId;


-- 2. Which country’s brewery produces the beers with highest average smell rating?
SELECT BreweryCountry, FORMAT(AVG(SmellScore),2) as SMELL_SCORE
FROM Beers INNER JOIN Smells
  ON Smells.BeerId = Beers.BeerId
INNER JOIN Breweries
  ON Breweries.BreweryId = Beers.BreweryId
GROUP BY BreweryCountry
ORDER BY SMELL_SCORE DESC
LIMIT 1;


-- 3. Which users haven’t created a review?
SELECT Users.UserName
FROM Users LEFT OUTER JOIN Reviews
  ON Reviews.UserName = Users.UserName
  WHERE Reviews.ReviewId IS NULL;


-- 4. What is the most satisfying beer (beer with the highest score) for each user?
SELECT Scores.UserName, Beers.BeerName, MAX(Score)
FROM Scores INNER JOIN Beers
  ON Scores.BeerId = Beers.BeerId
INNER JOIN Users
  ON Users.UserName = Scores.UserName
Group BY Users.UserName
ORDER BY Users.UserName ASC;


-- 5. What is the average number of reviews per user?
SELECT AVG(REVIEW_PER_USER) AS AVG_REVIEW_PER_USER
FROM (
  SELECT UserName, COUNT(*) AS REVIEW_PER_USER
  FROM Reviews
  GROUP BY UserName) AS PER_USER;


-- 6. What is the average score for the top 10 rated beers?
SELECT BeerName, FORMAT(AVG(Score),2) AS 'Average Score of Top 10 rated beer'
FROM Beers INNER JOIN Scores
  ON Scores.BeerId = Beers.BeerId
GROUP BY BeerName
ORDER BY AVG(Score) DESC
LIMIT 10;


-- 7. List the beer which looks better than its smell?
SELECT Beers.BeerName
FROM Beers INNER JOIN Smells
  ON Beers.BeerId = Smells.BeerId
INNER JOIN Looks
  ON Beers.BeerId = Looks.BeerId
WHERE
  Looks.LookScore > Smells.SmellScore
GROUP BY Beers.BeerName;


-- 8. What’s the average score of each ABV(Alcohol by volume) level?
SELECT Beers.BeerId, BeerName, FORMAT(AVG(Score),2) AS AvgScore,beers.BeerABV AS 'ABV'
FROM Beers INNER JOIN Scores
  ON Beers.BeerId = Scores.BeerId
GROUP BY beers.BeerABV, BeerId, BeerName
ORDER BY beers.BeerABV DESC;


-- 9. List beers with the perfect score in smell, look, feel, taste, and overall score categories
--    made by any user (All-Star Beer).
SELECT Beers.BeerName AS 'Perfect Beer'
FROM Beers INNER JOIN Smells
  ON Beers.BeerId = Smells.BeerId
INNER JOIN Looks
  ON Beers.BeerId = Looks.BeerId
INNER JOIN Feels
  ON Beers.BeerId = Feels.BeerId
INNER JOIN Tastes
  ON Beers.BeerId = Tastes.BeerId
INNER JOIN Overalls
  ON Beers.BeerId = Overalls.BeerId
WHERE Smells.SmellScore = 5 AND Looks.LookScore = 5 AND Feels.FeelScore = 5
AND Tastes.TasteScore = 5 AND Overalls.OverallScore = 5
GROUP By Beers.BeerName;


-- 10. List each state’s best scored beer in the United States and Canada, with the beer name,
--     score, state and country.
SELECT Beers.BeerCountry AS COUNTRY, Beers.BeerState AS STATE,
Beers.BeerName AS BEER, Max(Scores.Score) AS SCORE
From Beers INNER JOIN Scores
  ON Beers.BeerId = Scores.BeerId
WHERE Beers.BeerCountry = 'US' OR Beers.BeerCountry = 'CA'
GROUP BY Beers.BeerCountry, Beers.BeerState
ORDER BY Beers.BeerCountry DESC, Beers.BeerState ASC;

