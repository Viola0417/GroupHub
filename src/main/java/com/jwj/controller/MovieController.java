package com.jwj.controller;

import com.jwj.pojo.Movie;
import com.jwj.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("MovieServiceImpl")
    private MovieService movieService;

    //show all movies;
    @RequestMapping("/toMovie")
    public String toMovie(Model model) {
        List<Movie> movieList = movieService.queryAllMovie();
        model.addAttribute("movieList", movieList);
        return "adminMovie";
    }

    @RequestMapping("/toAddMovie")
    public String toAddMovie(Model model) {
        return "adminAddMovie";
    }

    @RequestMapping("/addMovie")
    public String addMovie(@RequestParam("movieName") String movieName, @RequestParam("movieYear") String movieYearStr,
                           @RequestParam("movieDescription") String movieDescription, Model model) {
        int movieYear = Integer.parseInt(movieYearStr);
        System.out.println("name => " + movieName + ", year => " + movieYear + ", description => " + movieDescription);

        // if this movie has been added(same name and same year)
        //prompt error msg
        String errorMsg = "";
        String msg = "";
        if (movieService.checkMovieExist(movieName) && (movieService.getYearByName(movieName) == movieYear)) {
            errorMsg = "This movie has been added before!";
            model.addAttribute("errorMsg", errorMsg);
            return "adminAddMovie";
        }
        //else add to database
        movieService.addMovie(new Movie(movieName, movieYear, movieDescription));
        msg = "Successfully add movie, return";
        model.addAttribute("msg", msg);
        return "adminAddMovie";
    }

    @RequestMapping("/toUpdateMovie")
    public String toUpdateMovie(int movieId, Model model) {
        System.out.println("movieId => " + movieId);
        Movie movie = movieService.queryMovieById(movieId);
        //System.out.println(movie.toString());
        model.addAttribute("queryMovie", movie);
        return "adminUpdateMovie";
    }

    @RequestMapping("/updateMovie")
    public String updateMovie(Movie movie) {
        movieService.updateMovie(movie);
        return "redirect:/movie/toMovie";
    }

    @RequestMapping("/deleteMovie")
    public String deleteMovie(int movieId) {
        movieService.deleteMovieById(movieId);
        return "redirect:/movie/toMovie";
    }

    @RequestMapping("/toUserMovie")
    public String toUserMovie(Model model) {
        List<Movie> movieList = movieService.queryAllMovie();
        /*
        System.out.println("-----toUserMovie------");
        for (Movie m: movieList) {
            System.out.println(m.toString());
        }
        System.out.println("-----toUserMovie------");
         */
        model.addAttribute("movieList", movieList);
        return "userMovie";
    }

    @RequestMapping("/userQueryMovie")
    public String userQueryMovieByName(@RequestParam("queryMovieName") String movieName, Model model) {
        List<Movie> movie = movieService.queryMovieByName(movieName);
        model.addAttribute("movieList", movie);
        return "userMovie";
    }
}
