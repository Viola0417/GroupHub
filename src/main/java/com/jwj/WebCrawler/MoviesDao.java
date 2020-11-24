package com.jwj.WebCrawler;

import java.sql.Connection;
import java.sql.Statement;

public class MoviesDao {

    private Util util;
    private Connection conn;
    private Statement st;
    public MoviesDao() throws Exception{
        util = new Util();
        conn = util.conn();
        st = conn.createStatement();
    }

    //Store movie information into the database
    public void insert(String title, Integer year, String desc, Integer totalRateNumber, double totalRateScore) throws Exception{
        // SQL to insert movie entry. Ensure this movie has not been added before
        String sql = "INSERT INTO movie(movieName, movieYear, description, totalRateNumber, totalRateScore)\n " +
        "SELECT * FROM (SELECT '"+title.replaceAll("'", "")+"', "+year+", '"+desc.replaceAll("'", "")+"', "+totalRateNumber+", "+totalRateScore+") AS tmp \n" +
        "WHERE NOT EXISTS (SELECT movieName FROM movie WHERE movieName = '"+title.replaceAll("'", "")+"' AND movieYear = "+year+")" +
        " LIMIT 1;";

        //System.out.println(sql);
        st.executeUpdate(sql);
    }
}
