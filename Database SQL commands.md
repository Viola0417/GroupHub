# Database SQL commands

username: root 

Password:123456

Database Name: grouphub

```sql
CREATE DATABASE grouphub;

USE grouphub;
```


```SQL

## Admin Table without UserId

DROP TABLE IF EXISTS admin;

CREATE TABLE admin (adminName VARCHAR(100) NOT NULL, adminPassword VARCHAR(100) NOT NULL, PRIMARY KEY(adminName));

INSERT INTO admin(adminName, adminPassword) VALUES ('admin', '123456');

----

## User Table

DROP TABLE IF EXISTS user;

CREATE TABLE user (userName VARCHAR(100) NOT NULL, userPassword VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL, status INT(1), noOfFriends INT(3), noOfGroups INT(3), PRIMARY KEY(userName));

INSERT INTO user (userName, userPassword, email, status, noOfFriends, noOfGroups) VALUES ('grouphuber', '123456', 'congcoj@uci.edu', 1, 10, 3);
INSERT INTO user (userName, userPassword, email, status, noOfFriends, noOfGroups) VALUES ('Ben', '666', 'ben@uci.edu', 1, 0, 3);
----
## Movie TABLE 
DROP TABLE IF EXISTS movie;

CREATE TABLE movie (movieId INT(6) NOT NULL auto_increment, movieName VARCHAR(100) NOT NULL, movieYear INT(4) NOT NULL, description VARCHAR(50000) NOT NULL, totalRateNumber INT(6), totalRateScore Double(10,1), PRIMARY KEY(movieId));

INSERT INTO movie (movieName, movieYear, description, totalRateNumber, totalRateScore) VALUES ('Titanic', 1997, 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.', 0, 0.0);

----
## Book TABLE 
DROP TABLE IF EXISTS book;

CREATE TABLE book (bookId INT(6) NOT NULL auto_increment, bookName VARCHAR(100) NOT NULL, bookAuthor VARCHAR(30) NOT NULL, description VARCHAR(50000) NOT NULL, totalRateNumber INT(6), totalRateScore Double(10,1), PRIMARY KEY(bookId));
INSERT INTO book (bookName, bookAuthor, description, totalRateNumber, totalRateScore) VALUES ('Gone with the Wind', 'Margaret Mitchell', 'A manipulative woman and a roguish man conduct a turbulent romance during the American Civil War and Reconstruction periods.', 0, 0.0);

----
## Travel TABLE 
DROP TABLE IF EXISTS travel;

CREATE TABLE travel (travelId INT(6) NOT NULL auto_increment, travelName VARCHAR(100) NOT NULL, travelCountry VARCHAR(30) NOT NULL, description VARCHAR(50000) NOT NULL, totalRateNumber INT(6), totalRateScore Double(10,1), PRIMARY KEY(travelId));
INSERT INTO travel (travelName, travelCountry, description, totalRateNumber, totalRateScore) VALUES ('Hawaii', 'US', 'Hawaii is the world''s largest island chain, and it''s the only U.S. state completely made up of islands.', 0, 0.0);

----

## Rate TABLE 

DROP TABLE IF EXISTS rate;

## categoryType: 1 -> movie, 2 -> book, 3 -> travel

CREATE TABLE rate (rateId INT(6) NOT NULL auto_increment, rateAuthor VARCHAR(100) NOT NULL, rateTitle VARCHAR(100) NOT NULL,                                                                                                                              
rateScore DOUBLE(2, 1) NOT NULL, rateCreateTime DATETIME(2) NOT NULL, rateContent VARCHAR(50000) NOT NULL, rateCategoryType INT(3) NOT NULL, 
rateCategoryId INT(6) NOT NULL, rateTotalLike INT(6) NOT NULL, rateTotalReply INT(6) NOT NULL, PRIMARY KEY(rateId));

Insert into rate (rateAuthor, rateTitle, rateScore, rateCreateTime, rateContent, rateCategoryType, rateCategoryId, rateTotalLike, rateTotalReply) VALUES 
("grouphuber", "Why only a 3?", 4.0, '2020-11-05 08:30:00', "There is no movie which made a bigger emotional impact on me than Titanic. And even in 2020, 23 years later, it has lost none of its magic.", 
1, 1, 0, 0);

Insert into rate (rateAuthor, rateTitle, rateScore, rateCreateTime, rateContent, rateCategoryType, rateCategoryId, rateTotalLike, rateTotalReply) VALUES 
("Ben", "One of the best movie ever made", 5.0, '2020-11-07 08:30:00', "I avoided watching this film for the longest time. Long before it was even released I had dismissed it as an over-hyped, over-blown, overly romanticized piece of Hollywood schmaltz, and I wanted nothing to do with it.", 
1, 1, 0, 0);

Insert into rate (rateAuthor, rateTitle, rateScore, rateCreateTime, rateContent, rateCategoryType, rateCategoryId, rateTotalLike, rateTotalReply) VALUES 
("test", "test", 5.0, '2020-11-07 08:30:00', "test", 1, 1, 0, 0);

----

## Comment TABLE 
