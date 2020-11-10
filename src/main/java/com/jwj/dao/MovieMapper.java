package com.jwj.dao;

import com.jwj.pojo.Movie;

import java.util.List;

public interface MovieMapper {

    //query All movie
    List<Movie> queryAllMovie();

    //add a new movie
    int addMovie(Movie movie);

    //get movie year by its name
    int getYearByName(String movieName);

    //check whether this movie is in db
    int checkMovieExist(String movieName);

    //delete a movie by MovieId
    int deleteMovieById(int movieId);

    //update a movie
    int updateMovie(Movie movie);

    //query movie by id
    Movie queryMovieById(int movieId);

    //query movie by name
    List<Movie> queryMovieByName(String movieName);
}
