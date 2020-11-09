package com.jwj.service;

import com.jwj.dao.MovieMapper;
import com.jwj.pojo.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieMapper movieMapper;

    public void setMovieMapper(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    //query All movie
    public List<Movie> queryAllMovie() {
        return movieMapper.queryAllMovie();
    }

    //add a new movie
    public int addMovie(Movie movie) {
        return movieMapper.addMovie(movie);
    }

    //get movie year by its name
    public int getYearByName(String movieName) {
        return movieMapper.getYearByName(movieName);
    }

    //check whether this movie is in db
    public boolean checkMovieExist(String movieName) {
        return movieMapper.checkMovieExist(movieName) > 0;
    }

    //delete a movie by MovieId
    public int deleteMovieById(int movieId) {
        return movieMapper.deleteMovieById(movieId);
    }

    //update a movie
    public int updateMovie(Movie movie) {
        return movieMapper.updateMovie(movie);
    }

    //query movie by id
    public Movie queryMovieById(int movieId) {
        return movieMapper.queryMovieById(movieId);
    }
}
