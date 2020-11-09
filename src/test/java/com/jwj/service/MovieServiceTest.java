package com.jwj.service;

import com.jwj.pojo.Movie;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MovieServiceTest {
    @Test
    public void testQueryAllMovie() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MovieService movieServiceImpl = (MovieService) context.getBean("MovieServiceImpl");
        List<Movie> movieList = movieServiceImpl.queryAllMovie();
        for (Movie m: movieList) {
            System.out.println(m.toString());
        }
    }

    @Test
    public void testAddMovie() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MovieService movieServiceImpl = (MovieService) context.getBean("MovieServiceImpl");
        String movieName = "The Revenant";
        int movieYear = 2015;
        String movieDescription = "A frontiersman on a fur trading expedition in the 1820s fights for survival after " +
                "being mauled by a bear and left for dead by members of his own hunting team.";
        Movie movie = new Movie(movieName, movieYear, movieDescription);
        movieServiceImpl.addMovie(movie);
        System.out.println("add movie completed!");
    }

    @Test
    public void testGetYearByName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MovieService movieServiceImpl = (MovieService) context.getBean("MovieServiceImpl");
        String movieName = "Titanic";
        int movieYear = movieServiceImpl.getYearByName(movieName);
        System.out.println("year => " + movieYear);
    }

    @Test
    public void testCheckMovieExist() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MovieService movieServiceImpl = (MovieService) context.getBean("MovieServiceImpl");
        String movieName = "Titanicv";
        boolean res = movieServiceImpl.checkMovieExist(movieName);
        if (res) {
            System.out.println("This movie is in DB!");
        } else {
            System.out.println("This movie is not in DB!");
        }
    }

    @Test
    public void testDeleteMovieById() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MovieService movieServiceImpl = (MovieService) context.getBean("MovieServiceImpl");
        int id = 4;
        movieServiceImpl.deleteMovieById(id);
        System.out.println("Deletion completed!");
    }

    @Test
    public void testQueryMovieById() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MovieService movieServiceImpl = (MovieService) context.getBean("MovieServiceImpl");
        int id = 1;
        Movie movie = movieServiceImpl.queryMovieById(1);
        System.out.println(movie.toString());
    }
}
