package com.jwj.controller;

import com.jwj.pojo.Movie;
import com.jwj.pojo.Book;
import com.jwj.pojo.Travel;
import com.jwj.pojo.Rate;
import com.jwj.service.MovieService;
import com.jwj.service.BookService;
import com.jwj.service.TravelService;
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
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
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
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
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


    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @RequestMapping("/toBookRate")
    public String toBookRate(@RequestParam("bookId") String bookIdStr, Model model, HttpSession session) {
        int bookId = Integer.parseInt(bookIdStr);

        Book currentBook = bookService.queryBookById(bookId);

        session.setAttribute("bookId", bookId);
        List<Rate> rateList = rateService.queryBookRate(bookId);
        String bookTitle = currentBook.getBookName() + " (" + currentBook.getBookAuthor() + ")";
        String bookReviews= currentBook.getTotalRateNumber() + " reviews";
        String bookScore = "";

        if (currentBook.getTotalRateNumber() > 0) {
            Double avgScore = currentBook.getTotalRateScore() / currentBook.getTotalRateNumber();
            String avgScoreStr = String.format("%.2f", avgScore);
            bookScore = "average rate score: " + avgScoreStr;
        } else {
            bookScore = "This movie has no rate now!";
        }

        model.addAttribute("rateList", rateList);
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("bookReviews", bookReviews);
        model.addAttribute("bookScore", bookScore);

        return "userBookRate";
    }

    @RequestMapping("/toAddBookRate")
    public String toAddBookRate(HttpSession session) {
        //set rateScore to be 0, for front end checking whether rate exists and get value
        session.setAttribute("bookRate", new Rate("",0.0, ""));

        int bookId = (Integer) session.getAttribute("bookId");
        String rateAuthor = (String) session.getAttribute("userName");

        // movie 1, book 2, travel 3
        // we only allow one user rate once to one entity for each category
        // so if he has rated, the rateList has one rate record, or it will be empty
        Rate currentRate = new Rate(rateAuthor, 2, bookId);
        List<Rate> rateList = rateService.queryBookRateByAuthor(currentRate);
        if (rateList.isEmpty()) {
            System.out.println("not rate!");
        } else {
            Rate formalRate = new Rate(rateList.get(0).getRateTitle(), rateList.get(0).getRateScore(),
                    rateList.get(0).getRateContent());
            session.setAttribute("bookRate", formalRate);
            session.setAttribute("rateId", rateList.get(0).getRateId());
        }
        return "addBookRate";
    }

    @RequestMapping("/addRateForBook")
    public String addRateForBook(@RequestParam("rateScore") String rateScore, @RequestParam("rateTitle") String rateTitle,
                          @RequestParam("rateContent") String rateContent, Model model, HttpSession session) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date rateCrateTime = sdf.parse(nowTime);
        String rateAuthor = (String) session.getAttribute("userName");
        int rateCategoryId = (Integer) session.getAttribute("bookId");
        int rateCategoryType = 2; //represent book
        int bookId = (Integer) session.getAttribute("bookId");

        //since we are adding book rate, the rateCategoryType is 2, representing book
        //when we first add rate, we set #reply and #like to be 0
        Rate rate = new Rate(rateAuthor, rateTitle, Double.parseDouble(rateScore), rateCrateTime, rateContent,
                rateCategoryType, rateCategoryId, 0, 0);
        rateService.addRateForBook(rate);

        //after add new rate, we update this book's total rate number and total score
        Book book = bookService.queryBookById(bookId);
        book.setTotalRateScore(book.getTotalRateScore() + Integer.parseInt(rateScore));
        book.setTotalRateNumber(book.getTotalRateNumber() + 1);
        bookService.updateBook(book);

        String successMsg = "successfully add a rate!";
        model.addAttribute("successMsg", successMsg);
        return "addBookRate";
    }

    @RequestMapping("/updateBookRate")
    public String updateBookRate(@RequestParam("rateId") String rateIdStr, @RequestParam("newRateScore") String newRateScoreStr, @RequestParam("newRateTitle") String newRateTitle,
                                  @RequestParam("newRateContent") String newRateContent, Model model, HttpSession session) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
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
        session.setAttribute("bookRate", newRate);

        //update related book score
        Book book = bookService.queryBookById(newRate.getRateCategoryId());
        Double newBookRateScore = book.getTotalRateScore() - originRateScore + newRateScore;
        book.setTotalRateScore(newBookRateScore);
        bookService.updateBook(book);

        return "redirect:/rate/updateBackToAddRateForBook";
    }

    @RequestMapping("/updateBackToAddRateForBook")
    public String updateBackToAddRateForBook(Model model) {
        String successMsg = "successfully submit your rate!";
        model.addAttribute("successMsg", successMsg);
        return "addBookRate";
    }

    @Autowired
    @Qualifier("TravelServiceImpl")
    private TravelService travelService;

    @RequestMapping("/toTravelRate")
    public String toTravelRate(@RequestParam("travelId") String travelIdStr, Model model, HttpSession session) {
        int travelId = Integer.parseInt(travelIdStr);

        Travel currentTravel = travelService.queryTravelById(travelId);

        session.setAttribute("travelId", travelId);
        List<Rate> rateList = rateService.queryTravelRate(travelId);
        String travelTitle = currentTravel.getTravelName() + " (" + currentTravel.getTravelCountry() + ")";
        String travelReviews= currentTravel.getTotalRateNumber() + " reviews";
        String travelScore = "";

        if (currentTravel.getTotalRateNumber() > 0) {
            Double avgScore = currentTravel.getTotalRateScore() / currentTravel.getTotalRateNumber();
            String avgScoreStr = String.format("%.2f", avgScore);
            travelScore = "average rate score: " + avgScoreStr;
        } else {
            travelScore = "This movie has no rate now!";
        }

        model.addAttribute("rateList", rateList);
        model.addAttribute("travelTitle", travelTitle);
        model.addAttribute("travelReviews", travelReviews);
        model.addAttribute("travelScore", travelScore);

        return "userTravelRate";
    }

    @RequestMapping("/toAddTravelRate")
    public String toAddTravelRate(HttpSession session) {
        //set rateScore to be 0, for front end checking whether rate exists and get value
        session.setAttribute("travelRate", new Rate("",0.0, ""));

        int travelId = (Integer) session.getAttribute("travelId");
        String rateAuthor = (String) session.getAttribute("userName");

        // movie 1, book 2, travel 3
        // we only allow one user rate once to one entity for each category
        // so if he has rated, the rateList has one rate record, or it will be empty
        Rate currentRate = new Rate(rateAuthor, 3, travelId);
        List<Rate> rateList = rateService.queryTravelRateByAuthor(currentRate);
        if (rateList.isEmpty()) {
            System.out.println("not rate!");
        } else {
            Rate formalRate = new Rate(rateList.get(0).getRateTitle(), rateList.get(0).getRateScore(),
                    rateList.get(0).getRateContent());
            session.setAttribute("travelRate", formalRate);
            session.setAttribute("rateId", rateList.get(0).getRateId());
        }
        return "addTravelRate";
    }

    @RequestMapping("/addRateForTravel")
    public String addRateForTravel(@RequestParam("rateScore") String rateScore, @RequestParam("rateTitle") String rateTitle,
                                 @RequestParam("rateContent") String rateContent, Model model, HttpSession session) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date rateCrateTime = sdf.parse(nowTime);
        String rateAuthor = (String) session.getAttribute("userName");
        int rateCategoryId = (Integer) session.getAttribute("travelId");
        int rateCategoryType = 3; //represent travel
        int travelId = (Integer) session.getAttribute("travelId");

        //since we are adding travel rate, the rateCategoryType is 3, representing travel
        //when we first add rate, we set #reply and #like to be 0
        Rate rate = new Rate(rateAuthor, rateTitle, Double.parseDouble(rateScore), rateCrateTime, rateContent,
                rateCategoryType, rateCategoryId, 0, 0);
        rateService.addRateForTravel(rate);

        //after add new rate, we update this travel's total rate number and total score
        Travel travel = travelService.queryTravelById(travelId);
        travel.setTotalRateScore(travel.getTotalRateScore() + Integer.parseInt(rateScore));
        travel.setTotalRateNumber(travel.getTotalRateNumber() + 1);
        travelService.updateTravel(travel);

        String successMsg = "successfully add a rate!";
        model.addAttribute("successMsg", successMsg);
        return "addTravelRate";
    }

    @RequestMapping("/updateTravelRate")
    public String updateTravelRate(@RequestParam("rateId") String rateIdStr, @RequestParam("newRateScore") String newRateScoreStr, @RequestParam("newRateTitle") String newRateTitle,
                                 @RequestParam("newRateContent") String newRateContent, Model model, HttpSession session) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
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
        session.setAttribute("travelRate", newRate);

        //update related travel score
        Travel travel = travelService.queryTravelById(newRate.getRateCategoryId());
        Double newTravelRateScore = travel.getTotalRateScore() - originRateScore + newRateScore;
        travel.setTotalRateScore(newTravelRateScore);
        travelService.updateTravel(travel);

        return "redirect:/rate/updateBackToAddRateForTravel";
    }

    @RequestMapping("/updateBackToAddRateForTravel")
    public String updateBackToAddRateForTravel(Model model) {
        String successMsg = "successfully submit your rate!";
        model.addAttribute("successMsg", successMsg);
        return "addTravelRate";
    }
}