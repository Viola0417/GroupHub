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

----

## Category Table

DROP TABLE IF EXISTS category;

CREATE TABLE category (categoryId INT(3) NOT NULL auto_increment, categoryType VARCHAR(10) NOT NULL, categoryName VARCHAR(100) NOT NULL, description VARCHAR(50000) NOT NULL, ratings DOUBLE(2,1) NOT NULL, PRIMARY KEY(categoryId));

INSERT INTO category (categoryType, categoryName, description, ratings) VALUES ('movies', 'Titanic', 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.', 0.0);
INSERT INTO category (categoryType, categoryName, description, ratings) VALUES ('books', 'Gone with the Wind', 'A manipulative woman and a roguish man conduct a turbulent romance during the American Civil War and Reconstruction periods.', 0.0);
INSERT INTO category (categoryType, categoryName, description, ratings) VALUES ('travel', 'Hawaii', '. Hawaii is the world''s largest island chain, and it''s the only U.S. state completely made up of islands.', 0.0);


----

## Topic Table

DROP TABLE IF EXISTS topic;

CREATE TABLE topic (topicId INT(3) NOT NULL, noOfPosts INT(6) NOT NULL, topicContent VARCHAR(50000) NOT NULL, createdTime DATETIME(2) NOT NULL, ratings DOUBLE(2,1), userId VARCHAR(100) NOT NULL, categoryId INT(3) NOT NULL, PRIMARY KEY(topicId), FOREIGN KEY(userId) REFERENCES user(userName), FOREIGN KEY(categoryId) REFERENCES category(categoryId));

INSERT INTO topic (topicId, noOfPosts, topicContent, createdTime, ratings, userId, categoryId) VALUES (1, 1, 'Titanic is world famous. The film that broke every box office record on release and wore the box office champion crown for many years(until it was overtaken by Jim Cameron''s Avatar) is special because not only does it showcase the Titanic disaster in all its glory the film is a technical achievement of the highest order.', '2020-11-02 14:00:00', 8.2, 'grouphuber', 1);

----

## Post Table

DROP TABLE IF EXISTS post;

CREATE TABLE post (postId INT(6) NOT NULL auto_increment, postContent VARCHAR(50000) NOT NULL, createdTime DATETIME(2) NOT NULL, parentId INT(6), topicId INT(3) NOT NULL, PRIMARY KEY(postId), FOREIGN KEY(topicId) REFERENCES topic(topicId));

INSERT INTO post (postContent, createdTime, parentId, topicId) VALUES ('Titanic is world famous. The film that broke every box office record on release and wore the box office champion crown for many years(until it was overtaken by Jim Cameron''s Avatar) is special because not only does it showcase the Titanic disaster in all its glory the film is a technical achievement of the highest order.', '2020-11-02 14:30:00', NULL, 1);
INSERT INTO post (postContent, createdTime, parentId, topicId) VALUES ('I agree!.', '2020-11-04 11:20:00', 1, 1);
