package com.jwj.WebCrawler;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {

    //Database Connectivity
    public Connection conn() throws Exception{

        String username="root";
        String password="123456";
        String url="jdbc:mysql://localhost:3306/grouphub";
        String driver="com.mysql.cj.jdbc.Driver";
        System.out.println(username);
        //
        // Database Connectivity: First load the driver
        Class.forName(driver);
        // Establish a database connection
        Connection conn=DriverManager.getConnection(url,username,password);
        return conn;
    }
}
