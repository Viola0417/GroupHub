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
    public void insertMovie(String title, Integer year, String desc, Integer totalRateNumber, double totalRateScore) throws Exception{
        // SQL to insert movie entry. Ensure this movie has not been added before
        String movieSql = "INSERT INTO movie(movieName, movieYear, description, totalRateNumber, totalRateScore)\n " +
        "SELECT * FROM (SELECT '"+title.replaceAll("'", "")+"', "+year+", '"+desc.replaceAll("'", "")+"', "+totalRateNumber+", "+totalRateScore+") AS tmp \n" +
        "WHERE NOT EXISTS (SELECT movieName FROM movie WHERE movieName = '"+title.replaceAll("'", "")+"' AND movieYear = "+year+")" +
        " LIMIT 1;";

        System.out.println(movieSql);
        st.executeUpdate(movieSql);
    }

    //Store movie information into the database
    public void insertBook(String title, String author, String desc, Integer totalRateNumber, double totalRateScore) throws Exception{

        // SQL to insert movie entry. Ensure this movie has not been added before
        String bookSql = "INSERT INTO book(bookName, bookAuthor, description, totalRateNumber, totalRateScore)\n " +
                "SELECT * FROM (SELECT '"+title+"', '"+author+"', '"+desc.replaceAll("'", "")+"', "+totalRateNumber+", "+totalRateScore+") AS tmp \n" +
                "WHERE NOT EXISTS (SELECT bookName FROM book WHERE bookName = '"+title+"' AND bookAuthor = '"+author+"')" +
                " LIMIT 1;";

        //System.out.println(bookSql);
        st.executeUpdate(bookSql);
    }

    //Store movie information into the database
    public void insertTravel(String title, String country, String desc, Integer totalRateNumber, double totalRateScore) throws Exception{

        // SQL to insert travel entry. Ensure this movie has not been added before
        String travelSql = "INSERT INTO travel(travelName, travelCountry, description, totalRateNumber, totalRateScore)\n " +
                "SELECT * FROM (SELECT '"+title+"', '"+country+"', '"+desc.replaceAll("'", "")+"', "+totalRateNumber+", "+totalRateScore+") AS tmp \n" +
                "WHERE NOT EXISTS (SELECT travelName FROM travel WHERE travelName = '"+title+"' AND travelCountry = '"+country+"')" +
                " LIMIT 1;";

        //System.out.println(travelSql);
        st.executeUpdate(travelSql);
    }

}
