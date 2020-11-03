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

CREATE TABLE category (categoryId INT(3) NOT NULL auto_increment, categoryName VARCHAR(100) NOT NULL, description VARCHAR(50000) NOT NULL, ratings DOUBLE(2,1) NOT NULL, PRIMARY KEY(categoryId));

INSERT INTO category (categoryId, categoryName, description, ratings) VALUES (1, 'movies', 'This category provides different movies for students to discuss with each other', 7.2);

----

## Topic Table

DROP TABLE IF EXISTS topic;

CREATE TABLE topic (topicId INT(3) NOT NULL, noOfPosts INT(6) NOT NULL, topicContent VARCHAR(50000) NOT NULL, createdTime DATETIME(2) NOT NULL, ratings DOUBLE(2,1), userId VARCHAR(100) NOT NULL, categoryId INT(3) NOT NULL, PRIMARY KEY(topicId), FOREIGN KEY(userId) REFERENCES user(userName), FOREIGN KEY(categoryId) REFERENCES category(categoryId));

INSERT INTO topic (topicId, noOfPosts, topicContent, createdTime, ratings, userId, categoryId) VALUES (1, 50, 'This topic is to discuss the movie \'Students at UC Irvine\'', '2020-11-02 14:00:00', 8.2, 'grouphuber', 1);

----

## Post Table

DROP TABLE IF EXISTS post;

CREATE TABLE post (postId INT(6) NOT NULL auto_increment, postContent VARCHAR(50000) NOT NULL, createdTime DATETIME(2) NOT NULL, parentId INT(6), topicId INT(3) NOT NULL, PRIMARY KEY(postId), FOREIGN KEY(topicId) REFERENCES topic(topicId));

INSERT INTO post (postId, postContent, createdTime, parentId, topicId) VALUES (1, 'I love the movie \'Students at UC Irvine\'', '2020-11-02 14:30:00', NULL, 1);