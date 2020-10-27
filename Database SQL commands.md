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
CREATE TABLE admin (
adminName VARCHAR(100) NOT NULL,
adminPassword VARCHAR(100) NOT NULL,
KEY adminName(adminName)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO admin(adminName, adminPassword) VALUES
('admin','123456');


```

![Screen Shot 2020-10-26 at 10.29.05 PM](/Users/luyaojin/Library/Application Support/typora-user-images/Screen Shot 2020-10-26 at 10.29.05 PM.png)

----

## more tables to be added 