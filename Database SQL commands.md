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

CREATE TABLE movie (movieId INT(6) NOT NULL auto_increment, movieName VARCHAR(100) NOT NULL, movieYear INT(4) NOT NULL, description VARCHAR(50000) NOT NULL, totalRateNumber INT(6), totalRateScore Double(2,1), PRIMARY KEY(movieId));

INSERT INTO movie (movieName, movieYear, description, totalRateNumber, totalRateScore) VALUES ('Titanic', 1997, 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.', 0, 0.0);

----
## Book TABLE 
DROP TABLE IF EXISTS book;

CREATE TABLE book (bookId INT(6) NOT NULL auto_increment, bookName VARCHAR(100) NOT NULL, bookAuthor VARCHAR(30) NOT NULL, description VARCHAR(50000) NOT NULL, totalRateNumber INT(6), totalRateScore Double(2,1), PRIMARY KEY(bookId));
INSERT INTO book (bookName, bookAuthor, description, totalRateNumber, totalRateScore) VALUES ('Gone with the Wind', 'Margaret Mitchell', 'A manipulative woman and a roguish man conduct a turbulent romance during the American Civil War and Reconstruction periods.', 0, 0.0);

----
## Travel TABLE 
DROP TABLE IF EXISTS travel;

CREATE TABLE travel (travelId INT(6) NOT NULL auto_increment, travelName VARCHAR(100) NOT NULL, travelCountry VARCHAR(30) NOT NULL, description VARCHAR(50000) NOT NULL, totalRateNumber INT(6), totalRateScore Double(2,1), PRIMARY KEY(travelId));
INSERT INTO travel (travelName, travelCountry, description, totalRateNumber, totalRateScore) VALUES ('Hawaii', 'US', 'Hawaii is the world''s largest island chain, and it''s the only U.S. state completely made up of islands.', 0, 0.0);

----

## Post Table

DROP TABLE IF EXISTS post;

CREATE TABLE post (postId INT(6) NOT NULL auto_increment, postUserName VARCHAR(100) NOT NULL, categoryType INT(2), categoryId INT(6), rootId INT(6) NOT NULL, postContent VARCHAR(50000) NOT NULL, createdTime DATETIME(2) NOT NULL, previousPostId INT(6) NOT NULL, ratingScore DOUBLE(2, 1), totalReply INT(6), PRIMARY KEY(postId));

## rootId is the parent post, if rootId is 0, means this post is the original post
## if a post is the first comment, its previous id should be 0
## if this post is not the parent commit, it will not have ratingScore, we can store it as -1.
## categoryType: 1 -> movie, 2 -> book, 3 -> travel

INSERT INTO post (categoryType, postUserName, categoryId, rootId, postContent, createdTime, previousPostId, ratingScore, totalReply) VALUES (1, "grouphuber", 1, 0, 'Titanic is world famous. The film that broke every box office record on release and wore the box office champion crown for many years(until it was overtaken by Jim Cameron''s Avatar) is special because not only does it showcase the Titanic disaster in all its glory the film is a technical achievement of the highest order.', '2020-11-02 14:30:00', 0, 4, 1);
INSERT INTO post (categoryType, postUserName, categoryId, rootId, postContent, createdTime, previousPostId, ratingScore, totalReply) VALUES (1, "Ben", 1, 1, 'I agree!.', '2020-11-04 11:20:00', 0, -1, 0);
