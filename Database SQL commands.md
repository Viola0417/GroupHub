# Database SQL commands

username: root 

Password:123456

Database Name: grouphub

```sql
CREATE DATABASE grouphub;

USE grouphub;
```



-----

## Admin Table

```SQL
DROP TABLE IF EXISTS admin;

CREATE TABLE admin (
adminId INT(3) PRIMARY KEY NOT NULL auto_increment,
adminName VARCHAR(100) NOT NULL,
adminPassword VARCHAR(100) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO admin(adminName, adminPassword) VALUES
('admin','123456');

----

## more tables to be added 