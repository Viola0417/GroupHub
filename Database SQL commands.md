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

CREATE TABLE category (categoryId INT(6) NOT NULL auto_increment, categoryType VARCHAR(10) NOT NULL, categoryName VARCHAR(100) NOT NULL, description VARCHAR(50000) NOT NULL, totalRateNumber INT(6), totalRateScore Double(2,1), PRIMARY KEY(categoryId));

INSERT INTO category (categoryType, categoryName, description, totalRateNumber, totalRateScore) VALUES ('movies', 'Titanic', 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.', 0, 0.0);
INSERT INTO category (categoryType, categoryName, description, totalRateNumber, totalRateScore) VALUES ('books', 'Gone with the Wind', 'A manipulative woman and a roguish man conduct a turbulent romance during the American Civil War and Reconstruction periods.', 0, 0.0);
INSERT INTO category (categoryType, categoryName, description, totalRateNumber, totalRateScore) VALUES ('travel', 'Hawaii', '. Hawaii is the world''s largest island chain, and it''s the only U.S. state completely made up of islands.', 0, 0.0);


----

## Post Table

DROP TABLE IF EXISTS post;

CREATE TABLE post (postId INT(6) NOT NULL auto_increment, categoryId INT(6), rootId INT(6) NOT NULL, postContent VARCHAR(50000) NOT NULL, createdTime DATETIME(2) NOT NULL, previousPostId INT(6) NOT NULL, ratingScore DOUBLE(2, 1), totalLike INT(6), totalReply INT(6), PRIMARY KEY(postId), FOREIGN KEY(categoryId) REFERENCES category(categoryId));

## rootId is the parent post, if rootId is 0, means this post is the original post
## if a post is the first comment, its previous id should be 0
## if this post is not the parent commit, it will not have ratingScore, we can store it as -1.

INSERT INTO post (categoryId, rootId, postContent, createdTime, previousPostId, ratingScore, totalLike, totalReply) VALUES (1, 0, 'Titanic is world famous. The film that broke every box office record on release and wore the box office champion crown for many years(until it was overtaken by Jim Cameron''s Avatar) is special because not only does it showcase the Titanic disaster in all its glory the film is a technical achievement of the highest order.', '2020-11-02 14:30:00', 0, 4, 0, 0);
INSERT INTO post (categoryId, rootId, postContent, createdTime, previousPostId, ratingScore, totalLike, totalReply) VALUES (1, 1, 'I agree!.', '2020-11-04 11:20:00', 0, -1, 0, 0);
