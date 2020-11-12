package com.jwj.controller;

import com.jwj.pojo.Movie;
import com.jwj.pojo.Rate;
import com.jwj.service.MovieService;
import com.jwj.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/rate")
public class RateController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("RateServiceImpl")
    private RateService rateService;

    @Autowired
    @Qualifier("MovieServiceImpl")
    private MovieService movieService;

    @RequestMapping("/toMovieRate")
    public String toMovieRate(@RequestParam("movieId") String movieIdStr, Model model, HttpSession session) {
        int movieId = Integer.parseInt(movieIdStr);

        Movie currentMovie = movieService.queryMovieById(movieId);

        //we assume categoryType for rate: movie -> 1, book -> 2, travel -> 3
        //here we want to show all rates for movie

        session.setAttribute("movieId", movieId);
        List<Rate> rateList = rateService.queryMovieRate(movieId);
        String movieTitle = currentMovie.getMovieName() + " (" + Integer.toString(currentMovie.getMovieYear()) + ")";
        String movieReviews= currentMovie.getTotalRateNumber() + " reviews";
        String movieScore = "";

        if (currentMovie.getTotalRateNumber() > 0) {
            Double avgScore = currentMovie.getTotalRateScore() / currentMovie.getTotalRateNumber();
            String avgScoreStr = String.format("%.2f", avgScore);
            movieScore = "average rate score: " + avgScoreStr;
        } else {
            movieScore = "This movie has no rate now!";
        }

        model.addAttribute("rateList", rateList);
        model.addAttribute("movieTitle", movieTitle);
        model.addAttribute("movieReviews", movieReviews);
        model.addAttribute("movieScore", movieScore);

        return "userMovieRate";
    }

    @RequestMapping("/toAddMovieRate")
    public String toAddMovieRate(HttpSession session) {
        //set rateScore to be 0, for front end checking whether rate exists and get value
        session.setAttribute("movieRate", new Rate("",0.0, ""));

        int movieId = (Integer) session.getAttribute("movieId");
        String rateAuthor = (String) session.getAttribute("userName");

        // movie 1, book 2, travel 3
        // we only allow one user rate once to one movie
        // so if he has rated, the rateList has one rate record, or it will be empty
        Rate currentRate = new Rate(rateAuthor, 1, movieId);
        List<Rate> rateList = rateService.queryMovieRateByAuthor(currentRate);
        if (rateList.isEmpty()) {
            System.out.println("not rate!");
        } else {
            Rate formalRate = new Rate(rateList.get(0).getRateTitle(), rateList.get(0).getRateScore(),
                    rateList.get(0).getRateContent());
            session.setAttribute("movieRate", formalRate);
            session.setAttribute("rateId", rateList.get(0).getRateId());
        }
        return "addMovieRate";
    }

    @RequestMapping("/addRate")
    public String addRate(@RequestParam("rateScore") String rateScore, @RequestParam("rateTitle") String rateTitle,
                          @RequestParam("rateContent") String rateContent, Model model, HttpSession session) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date rateCrateTime = sdf.parse(nowTime);
        String rateAuthor = (String) session.getAttribute("userName");
        int rateCategoryId = (Integer) session.getAttribute("movieId");
        int rateCategoryType = 1; //represent movie
        int movieId = (Integer) session.getAttribute("movieId");

        //since we are adding movie rate, the rateCategoryType is 1, representing movie
        //when we first add rate, we set #reply and #like to be 0
        Rate rate = new Rate(rateAuthor, rateTitle, Double.parseDouble(rateScore), rateCrateTime, rateContent,
                rateCategoryType, rateCategoryId, 0, 0);
        rateService.addRate(rate);

        //after add new rate, we update this movie's total rate number and total score
        Movie movie = movieService.queryMovieById(movieId);
        movie.setTotalRateScore(movie.getTotalRateScore() + Integer.parseInt(rateScore));
        movie.setTotalRateNumber(movie.getTotalRateNumber() + 1);
        movieService.updateMovie(movie);

        String successMsg = "successfully add a rate!";
        model.addAttribute("successMsg", successMsg);
        return "addMovieRate";
    }

    @RequestMapping("/updateMovieRate")
    public String updateMovieRate(@RequestParam("rateId") String rateIdStr, @RequestParam("newRateScore") String newRateScoreStr, @RequestParam("newRateTitle") String newRateTitle,
                                  @RequestParam("newRateContent") String newRateContent, Model model, HttpSession session) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date rateTime = sdf.parse(nowTime);
        int rateId = Integer.parseInt(rateIdStr);

        //update rate title, score and content
        Rate originRate = rateService.queryRateById(rateId);
        Double originRateScore = originRate.getRateScore();
        Double newRateScore = Double.parseDouble(newRateScoreStr);
        Rate tempRate = new Rate(rateId, newRateTitle, newRateScore, rateTime, newRateContent);
        rateService.updateRate(tempRate);
        Rate newRate = rateService.queryRateById(rateId);
        session.setAttribute("movieRate", newRate);

        //update related movie score
        Movie movie = movieService.queryMovieById(newRate.getRateCategoryId());
        Double newMovieRateScore = movie.getTotalRateScore() - originRateScore + newRateScore;
        movie.setTotalRateScore(newMovieRateScore);
        movieService.updateMovie(movie);

        return "redirect:/rate/updateBackToAddRate";
    }

    @RequestMapping("/updateBackToAddRate")
    public String updateBackToAddRate(Model model) {
        String successMsg = "successfully submit your rate!";
        model.addAttribute("successMsg", successMsg);
        return "addMovieRate";
    }
}