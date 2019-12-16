CREATE SCHEMA IF NOT EXISTS BeerFactAnalysis;
USE BeerFactAnalysis;

DROP TABLE IF EXISTS Scores;
DROP TABLE IF EXISTS Overalls;
DROP TABLE IF EXISTS Tastes;
DROP TABLE IF EXISTS Feels;
DROP TABLE IF EXISTS Looks;
DROP TABLE IF EXISTS Smells;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Beers;
DROP TABLE IF EXISTS Breweries;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
  UserName VARCHAR(255),
  UserId int,
  CONSTRAINT pk_Users_UserName
    PRIMARY KEY (UserName)
);

CREATE TABLE Breweries (
  BreweryId VARCHAR(255),
  BreweryName VARCHAR(255),
  BreweryCountry VARCHAR(255),
  BreweryState VARCHAR(255),
  CONSTRAINT pk_Breweries_BreweryId
    PRIMARY KEY (BreweryId)
);

CREATE TABLE Beers (
  BeerId VARCHAR(255) NOT NULL,
  BeerName VARCHAR(255),
  BreweryId VARCHAR(255),
  BeerCountry VARCHAR(255),
  BeerState VARCHAR(255),
  BeerABV Double,
  CONSTRAINT pk_Beers_BeerId
    PRIMARY KEY (BeerId),
  CONSTRAINT fk_Beers_BreweryId
    FOREIGN KEY (BreweryId)
    REFERENCES Breweries(BreweryId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Reviews (
  ReviewId int AUTO_INCREMENT,
  ReviewDate Date,
  ReviewText LONGTEXT,
  BeerId VARCHAR(255),
  UserName VARCHAR(255),
  CONSTRAINT pk_Reviews_ReviewId
    PRIMARY KEY (ReviewId),
    
  CONSTRAINT fk_Reviews_BeerId
    FOREIGN KEY (BeerId)
    REFERENCES Beers(BeerId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Reviews_UserName
    FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Smells (
  SmellId int AUTO_INCREMENT,
  SmellScore VARCHAR(255),
  UserName VARCHAR(255),
  BeerId VARCHAR(255) NOT NULL,
  
  CONSTRAINT SmellId
    PRIMARY KEY (SmellId),
  CONSTRAINT fk_Smells_UserName
    FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Smells_BeerId
    FOREIGN KEY (BeerId)
    REFERENCES Beers(BeerId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Looks (
  LookId int AUTO_INCREMENT,
  LookScore VARCHAR(255),
  UserName VARCHAR(255),
  BeerId VARCHAR(255) NOT NULL,
  
  CONSTRAINT LookId
    PRIMARY KEY (LookId),
  CONSTRAINT fk_Looks_UserName
    FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Looks_BeerId
    FOREIGN KEY (BeerId)
    REFERENCES Beers(BeerId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Feels (
  FeelId int AUTO_INCREMENT,
  FeelScore VARCHAR(255),
  UserName VARCHAR(255),
  BeerId VARCHAR(255) NOT NULL,
  
  CONSTRAINT FeelId
    PRIMARY KEY (FeelId),
  CONSTRAINT fk_Feels_UserName
    FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Feels_BeerId
    FOREIGN KEY (BeerId)
    REFERENCES Beers(BeerId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Tastes (
  TasteId int AUTO_INCREMENT,
  TasteScore VARCHAR(255),
  UserName VARCHAR(255),
  BeerId VARCHAR(255) NOT NULL,
  
  CONSTRAINT TasteId
    PRIMARY KEY (TasteId),
  CONSTRAINT fk_Tastes_UserName
    FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Tastes_BeerId
    FOREIGN KEY (BeerId)
    REFERENCES Beers(BeerId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Overalls (
  OverallId int AUTO_INCREMENT,
  OverallScore VARCHAR(255),
  UserName VARCHAR(255),
  BeerId VARCHAR(255) NOT NULL,
  
  CONSTRAINT OverallId
    PRIMARY KEY (OverallId),
  CONSTRAINT fk_Overalls_UserName
    FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Overalls_BeerId
    FOREIGN KEY (BeerId)
    REFERENCES Beers(BeerId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Scores (
  ScoreId int AUTO_INCREMENT,
  Score VARCHAR(255),
  UserName VARCHAR(255),
  BeerId VARCHAR(255) NOT NULL,
  
  CONSTRAINT ScoreId
    PRIMARY KEY (ScoreId),
  CONSTRAINT fk_Scores_UserName
    FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Scores_BeerId
    FOREIGN KEY (BeerId)
    REFERENCES Beers(BeerId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/breweries.csv' INTO TABLE Breweries 
	FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n' 
    IGNORE 1 LINES (BreweryId, BreweryName, @dummy, BreweryState, BreweryCountry, @dummy, @dummy);
    
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/beers.csv' INTO TABLE Beers 
	FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n' 
    IGNORE 1 LINES (BeerId, BeerName, BreweryId, BeerState, BeerCountry, @dummy, @dummy, @inputABV, @dummy, @dummy) 
    SET BeerABV = IF(@inputABV = '', NULL, @inputABV); 

SET FOREIGN_KEY_CHECKS = 0;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/users.csv' IGNORE INTO TABLE Users 
	FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\r\n' 
    IGNORE 1 LINES (UserId, UserName);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/reviews.csv' INTO TABLE Reviews 
	FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES (ReviewId, BeerId, UserName, @ReviewDate, ReviewText) 
    SET ReviewDate = STR_TO_DATE(@ReviewDate, '%m/%d/%Y');
    
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/feel.csv' INTO TABLE Feels 
	FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\r\n' 
    IGNORE 1 LINES (@dummy, BeerId, UserName, FeelScore);
    
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/taste.csv' INTO TABLE Tastes 
	FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\r\n' 
    IGNORE 1 LINES (@dummy, BeerId, UserName, TasteScore);
    
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/look.csv' INTO TABLE Looks 
	FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\r\n' 
    IGNORE 1 LINES (@dummy, BeerId, UserName, LookScore);
    
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/smell.csv' INTO TABLE Smells 
	FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\r\n' 
    IGNORE 1 LINES (@dummy, BeerId, UserName, SmellScore);
    
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/overall.csv' INTO TABLE Overalls 
	FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\r\n' 
    IGNORE 1 LINES (@dummy, BeerId, UserName, OverallScore);
    
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/score.csv' INTO TABLE Scores 
	FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\r\n' 
    IGNORE 1 LINES (@dummy, BeerId, UserName, Score);

SET FOREIGN_KEY_CHECKS = 1;
