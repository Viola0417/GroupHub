package com.jwj.controller;

import com.jwj.pojo.Comment;
import com.jwj.pojo.Movie;
import com.jwj.pojo.Book;
import com.jwj.pojo.Travel;
import com.jwj.pojo.Rate;
import com.jwj.service.CommentService;
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
import java.util.*;

@Controller
@RequestMapping("/comment")
public class CommentController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("CommentServiceImpl")
    private CommentService commentService;

    @Autowired
    @Qualifier("RateServiceImpl")
    private RateService rateService;

    @Autowired
    @Qualifier("MovieServiceImpl")
    private MovieService movieService;

    @RequestMapping("/toAddComment")
    public String toAddComment(int rateId, HttpSession session, Model model) {
        System.out.println("current user is => " + session.getAttribute("userName"));
        System.out.println("rateID => " + rateId);
        System.out.println("add comment here!");
        int movieIdInt = (Integer) session.getAttribute("movieId");
        String movieId = Integer.toString(movieIdInt);
        System.out.println("movieId => " + movieId);
        model.addAttribute("movieId", movieId);
        return "redirect:/rate/toMovieRate";
    }

    @RequestMapping("addTopComment")
    public String addTopComment(@RequestParam("commentContent") String commentContent, @RequestParam("commentRateId") String commentRateId,
                                HttpSession session, Model model) throws ParseException {
        String errorMsg = "";
        int movieIdInt = (Integer) session.getAttribute("movieId");
        int commentId = Integer.parseInt(commentRateId);
        System.out.println("!!!commentId!!! => " + commentId);
        String movieId = Integer.toString(movieIdInt);

        if (commentContent == null || commentContent.length() == 0) {
            System.out.println("empty comment!");
            errorMsg = "comment content can not be empty!";
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("movieId", movieId);
            List<Rate> rateList = rateService.queryMovieRate(movieIdInt);
            Movie currentMovie = movieService.queryMovieById(movieIdInt);
            String movieTitle = currentMovie.getMovieName() + " (" + Integer.toString(currentMovie.getMovieYear()) + ")";
            String movieReviews = currentMovie.getTotalRateNumber() + " reviews";
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

        System.out.println("add top comment: rateid => " + commentRateId);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date commentCreateTime = sdf.parse(nowTime);

        String commentAuthor = (String)session.getAttribute("userName");
        int commentParentId = 0;
        int isDeleted = 0;

        Comment comment = new Comment(commentAuthor, commentContent, commentParentId, Integer.parseInt(commentRateId), commentCreateTime, isDeleted);
        commentService.addComment(comment);
        rateService.addCommentById(Integer.parseInt(commentRateId));

        model.addAttribute("movieId", movieId);
        model.addAttribute("rateId", commentRateId);
        //return "redirect:/rate/toMovieRate";
        return "redirect:/comment/showComment";
    }

    @RequestMapping("showComment")
    public String showComment(int rateId, Model model, HttpSession session) {
        System.out.println("rateId => " + rateId);
        Map<Comment, List<Comment>> commentHashMap = new LinkedHashMap<Comment, List<Comment>>();

        //1. find all top comments
        List<Comment> topCommentList = commentService.getTopCommentContentList(rateId);
        for (Comment comment: topCommentList) {
            int commentId = comment.getCommentId();
            List<Comment> replyCommentList = commentService.getReplyCommentList(commentId);
            commentHashMap.put(comment, replyCommentList);
        };

        int movieId = (Integer) session.getAttribute("movieId");
        Movie currentMovie = movieService.queryMovieById(movieId);
        List<Rate> rateList = rateService.queryMovieRate(movieId);
        String movieTitle = currentMovie.getMovieName() + " (" + Integer.toString(currentMovie.getMovieYear()) + ")";
        String movieReviews = currentMovie.getTotalRateNumber() + " reviews";
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
        model.addAttribute("commentHashMap", commentHashMap);
        model.addAttribute("rootRateId", rateId);

        return "userMovieRate";
    }

    @RequestMapping("/toAddReply")
    public String toAddReply(int commentParentId, HttpSession session, Model model) {
        System.out.println("to add reply");
        System.out.println("commenParentId => " + commentParentId);
        Comment parentComment = commentService.getCommentById(commentParentId);
        int clickRateId = parentComment.getCommentRateId();

        Map<Comment, List<Comment>> commentHashMap = new LinkedHashMap<Comment, List<Comment>>();

        //1. find all top comments
        List<Comment> topCommentList = commentService.getTopCommentContentList(clickRateId);
        for (Comment comment: topCommentList) {
            int commentId = comment.getCommentId();
            List<Comment> replyCommentList = commentService.getReplyCommentList(commentId);
            commentHashMap.put(comment, replyCommentList);
        };

        int movieId = (Integer) session.getAttribute("movieId");
        Movie currentMovie = movieService.queryMovieById(movieId);
        List<Rate> rateList = rateService.queryMovieRate(movieId);
        String movieTitle = currentMovie.getMovieName() + " (" + Integer.toString(currentMovie.getMovieYear()) + ")";
        String movieReviews = currentMovie.getTotalRateNumber() + " reviews";
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
        model.addAttribute("commentHashMap", commentHashMap);
        model.addAttribute("rootRateId", clickRateId);
        model.addAttribute("commentParentId", commentParentId);

        return "userMovieRate";
    }

    @RequestMapping("/addReply")
    public String addReply(@RequestParam("replyContent") String replyContent, @RequestParam("replyRateId") String replyRateId,
                           @RequestParam("replyParentId") String replyParentId, HttpSession session, Model model) throws ParseException {
        System.out.println("reply content => " + replyContent);
        System.out.println("reply rate id => " + replyRateId);
        System.out.println("reply parent id => " + replyParentId);

        int commentParentId = Integer.parseInt(replyParentId);

        //if reply to a reply, find the top comment(parentId = 0)
        Comment parentComment = commentService.getCommentById(commentParentId);
        while (parentComment.getCommentParentId() != 0) {
            commentParentId = parentComment.getCommentParentId();
            parentComment = commentService.getCommentById(commentParentId);
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date commentCreateTime = sdf.parse(nowTime);

        String commentAuthor = (String)session.getAttribute("userName");
        int isDeleted = 0;

        Comment comment = new Comment(commentAuthor, replyContent, parentComment.getCommentId(), Integer.parseInt(replyRateId), commentCreateTime, isDeleted);
        commentService.addComment(comment);
        rateService.addCommentById(Integer.parseInt(replyRateId));
        System.out.println("final parent id => " + parentComment.getCommentId());

        model.addAttribute("rateId", replyRateId);
        return "redirect:/comment/showComment";
    }

    // This is for Book
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @RequestMapping("/toAddCommentForBook")
    public String toAddCommentForBook(int rateId, HttpSession session, Model model) {
        System.out.println("current user is => " + session.getAttribute("userName"));
        System.out.println("rateID => " + rateId);
        System.out.println("add comment here!");
        int bookIdInt = (Integer) session.getAttribute("bookId");
        String bookId = Integer.toString(bookIdInt);
        System.out.println("bookId => " + bookId);
        model.addAttribute("bookId", bookId);
        return "redirect:/rate/toBookRate";
    }

    @RequestMapping("addTopCommentForBook")
    public String addTopCommentForBook(@RequestParam("commentContent") String commentContent, @RequestParam("commentRateId") String commentRateId,
                                HttpSession session, Model model) throws ParseException {
        String errorMsg = "";
        int bookIdInt = (Integer) session.getAttribute("bookId");
        int commentId = Integer.parseInt(commentRateId);
        System.out.println("!!!commentId!!! => " + commentId);
        String bookId = Integer.toString(bookIdInt);

        if (commentContent == null || commentContent.length() == 0) {
            System.out.println("empty comment!");
            errorMsg = "comment content can not be empty!";
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("bookId", bookId);
            List<Rate> rateList = rateService.queryBookRate(bookIdInt);
            Book currentBook = bookService.queryBookById(bookIdInt);
            String bookTitle = currentBook.getBookName() + " (" + currentBook.getBookAuthor() + ")";
            String bookReviews = currentBook.getTotalRateNumber() + " reviews";
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

        System.out.println("add top comment: rateid => " + commentRateId);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date commentCreateTime = sdf.parse(nowTime);

        String commentAuthor = (String)session.getAttribute("userName");
        int commentParentId = 0;
        int isDeleted = 0;

        Comment comment = new Comment(commentAuthor, commentContent, commentParentId, Integer.parseInt(commentRateId), commentCreateTime, isDeleted);
        commentService.addCommentForBook(comment);
        rateService.addCommentById(Integer.parseInt(commentRateId));

        model.addAttribute("bookId", bookId);
        model.addAttribute("rateId", commentRateId);
        return "redirect:/comment/showCommentForBook";
    }

    @RequestMapping("showCommentForBook")
    public String showCommentForBook(int rateId, Model model, HttpSession session) {
        System.out.println("rateId => " + rateId);
        Map<Comment, List<Comment>> commentHashMap = new LinkedHashMap<Comment, List<Comment>>();

        //1. find all top comments
        List<Comment> topCommentList = commentService.getTopCommentContentList(rateId);
        for (Comment comment: topCommentList) {
            int commentId = comment.getCommentId();
            List<Comment> replyCommentList = commentService.getReplyCommentList(commentId);
            commentHashMap.put(comment, replyCommentList);
        };

        int bookId = (Integer) session.getAttribute("bookId");
        Book currentBook = bookService.queryBookById(bookId);
        List<Rate> rateList = rateService.queryBookRate(bookId);
        String bookTitle = currentBook.getBookName() + " (" + currentBook.getBookAuthor() + ")";
        String bookReviews = currentBook.getTotalRateNumber() + " reviews";
        String bookScore = "";

        if (currentBook.getTotalRateNumber() > 0) {
            Double avgScore = currentBook.getTotalRateScore() / currentBook.getTotalRateNumber();
            String avgScoreStr = String.format("%.2f", avgScore);
            bookScore = "average rate score: " + avgScoreStr;
        } else {
            bookScore = "This book has no rate now!";
        }

        model.addAttribute("rateList", rateList);
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("bookReviews", bookReviews);
        model.addAttribute("bookScore", bookScore);
        model.addAttribute("commentHashMap", commentHashMap);
        model.addAttribute("rootRateId", rateId);

        return "userBookRate";
    }

    @RequestMapping("/toAddReplyForBook")
    public String toAddReplyForBook(int commentParentId, HttpSession session, Model model) {
        System.out.println("to add reply");
        System.out.println("commentParentId => " + commentParentId);
        Comment parentComment = commentService.getCommentById(commentParentId);
        int clickRateId = parentComment.getCommentRateId();

        Map<Comment, List<Comment>> commentHashMap = new LinkedHashMap<Comment, List<Comment>>();

        //1. find all top comments
        List<Comment> topCommentList = commentService.getTopCommentContentList(clickRateId);
        for (Comment comment: topCommentList) {
            int commentId = comment.getCommentId();
            List<Comment> replyCommentList = commentService.getReplyCommentList(commentId);
            commentHashMap.put(comment, replyCommentList);
        };

        int bookId = (Integer) session.getAttribute("bookId");
        Book currentBook = bookService.queryBookById(bookId);
        List<Rate> rateList = rateService.queryBookRate(bookId);
        String bookTitle = currentBook.getBookName() + " (" + currentBook.getBookAuthor() + ")";
        String bookReviews = currentBook.getTotalRateNumber() + " reviews";
        String bookScore = "";

        if (currentBook.getTotalRateNumber() > 0) {
            Double avgScore = currentBook.getTotalRateScore() / currentBook.getTotalRateNumber();
            String avgScoreStr = String.format("%.2f", avgScore);
            bookScore = "average rate score: " + avgScoreStr;
        } else {
            bookScore = "This book has no rate now!";
        }

        model.addAttribute("rateList", rateList);
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("bookReviews", bookReviews);
        model.addAttribute("bookScore", bookScore);
        model.addAttribute("commentHashMap", commentHashMap);
        model.addAttribute("rootRateId", clickRateId);
        model.addAttribute("commentParentId", commentParentId);

        return "userBookRate";
    }

    @RequestMapping("/addReplyForBook")
    public String addReplyForBook(@RequestParam("replyContent") String replyContent, @RequestParam("replyRateId") String replyRateId,
                           @RequestParam("replyParentId") String replyParentId, HttpSession session, Model model) throws ParseException {
        System.out.println("reply content => " + replyContent);
        System.out.println("reply rate id => " + replyRateId);
        System.out.println("reply parent id => " + replyParentId);

        int commentParentId = Integer.parseInt(replyParentId);

        //if reply to a reply, find the top comment(parentId = 0)
        Comment parentComment = commentService.getCommentById(commentParentId);
        while (parentComment.getCommentParentId() != 0) {
            commentParentId = parentComment.getCommentParentId();
            parentComment = commentService.getCommentById(commentParentId);
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date commentCreateTime = sdf.parse(nowTime);

        String commentAuthor = (String)session.getAttribute("userName");
        int isDeleted = 0;

        Comment comment = new Comment(commentAuthor, replyContent, parentComment.getCommentId(), Integer.parseInt(replyRateId), commentCreateTime, isDeleted);
        commentService.addCommentForBook(comment);
        rateService.addCommentById(Integer.parseInt(replyRateId));
        System.out.println("final parent id => " + parentComment.getCommentId());

        model.addAttribute("rateId", replyRateId);
        return "redirect:/comment/showCommentForBook";
    }

    // This is for Travel
    @Autowired
    @Qualifier("TravelServiceImpl")
    private TravelService travelService;

    @RequestMapping("/toAddCommentForTravel")
    public String toAddCommentForTravel(int rateId, HttpSession session, Model model) {
        System.out.println("current user is => " + session.getAttribute("userName"));
        System.out.println("rateID => " + rateId);
        System.out.println("add comment here!");
        int travelIdInt = (Integer) session.getAttribute("travelId");
        String travelId = Integer.toString(travelIdInt);
        System.out.println("travelId => " + travelId);
        model.addAttribute("travelId", travelId);
        return "redirect:/rate/toTravelRate";
    }

    @RequestMapping("addTopCommentForTravel")
    public String addTopCommentForTravel(@RequestParam("commentContent") String commentContent, @RequestParam("commentRateId") String commentRateId,
                                       HttpSession session, Model model) throws ParseException {
        String errorMsg = "";
        int travelIdInt = (Integer) session.getAttribute("travelId");
        int commentId = Integer.parseInt(commentRateId);
        System.out.println("!!!commentId!!! => " + commentId);
        String travelId = Integer.toString(travelIdInt);

        if (commentContent == null || commentContent.length() == 0) {
            System.out.println("empty comment!");
            errorMsg = "comment content can not be empty!";
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("travelId", travelId);
            List<Rate> rateList = rateService.queryTravelRate(travelIdInt);
            Travel currentTravel = travelService.queryTravelById(travelIdInt);
            String travelTitle = currentTravel.getTravelName() + " (" + currentTravel.getTravelCountry() + ")";
            String travelReviews = currentTravel.getTotalRateNumber() + " reviews";
            String travelScore = "";

            if (currentTravel.getTotalRateNumber() > 0) {
                Double avgScore = currentTravel.getTotalRateScore() / currentTravel.getTotalRateNumber();
                String avgScoreStr = String.format("%.2f", avgScore);
                travelScore = "average rate score: " + avgScoreStr;
            } else {
                travelScore = "This travel has no rate now!";
            }

            model.addAttribute("rateList", rateList);
            model.addAttribute("travelTitle", travelTitle);
            model.addAttribute("travelReviews", travelReviews);
            model.addAttribute("travelScore", travelScore);
            return "userTravelRate";
        }

        System.out.println("add top comment: rateid => " + commentRateId);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date commentCreateTime = sdf.parse(nowTime);

        String commentAuthor = (String)session.getAttribute("userName");
        int commentParentId = 0;
        int isDeleted = 0;

        Comment comment = new Comment(commentAuthor, commentContent, commentParentId, Integer.parseInt(commentRateId), commentCreateTime, isDeleted);
        commentService.addCommentForTravel(comment);
        rateService.addCommentById(Integer.parseInt(commentRateId));

        model.addAttribute("travelId", travelId);
        model.addAttribute("rateId", commentRateId);
        return "redirect:/comment/showCommentForTravel";
    }

    @RequestMapping("showCommentForTravel")
    public String showCommentForTravel(int rateId, Model model, HttpSession session) {
        System.out.println("rateId => " + rateId);
        Map<Comment, List<Comment>> commentHashMap = new LinkedHashMap<Comment, List<Comment>>();

        //1. find all top comments
        List<Comment> topCommentList = commentService.getTopCommentContentList(rateId);
        for (Comment comment: topCommentList) {
            int commentId = comment.getCommentId();
            List<Comment> replyCommentList = commentService.getReplyCommentList(commentId);
            commentHashMap.put(comment, replyCommentList);
        }

        int travelId = (Integer) session.getAttribute("travelId");
        Travel currentTravel = travelService.queryTravelById(travelId);
        List<Rate> rateList = rateService.queryTravelRate(travelId);
        String travelTitle = currentTravel.getTravelName() + " (" + currentTravel.getTravelCountry() + ")";
        String travelReviews = currentTravel.getTotalRateNumber() + " reviews";
        String travelScore = "";

        if (currentTravel.getTotalRateNumber() > 0) {
            Double avgScore = currentTravel.getTotalRateScore() / currentTravel.getTotalRateNumber();
            String avgScoreStr = String.format("%.2f", avgScore);
            travelScore = "average rate score: " + avgScoreStr;
        } else {
            travelScore = "This travel has no rate now!";
        }

        model.addAttribute("rateList", rateList);
        model.addAttribute("travelTitle", travelTitle);
        model.addAttribute("travelReviews", travelReviews);
        model.addAttribute("travelScore", travelScore);
        model.addAttribute("commentHashMap", commentHashMap);
        model.addAttribute("rootRateId", rateId);

        return "userTravelRate";
    }

    @RequestMapping("/toAddReplyForTravel")
    public String toAddReplyForTravel(int commentParentId, HttpSession session, Model model) {
        System.out.println("to add reply");
        System.out.println("commentParentId => " + commentParentId);
        Comment parentComment = commentService.getCommentById(commentParentId);
        int clickRateId = parentComment.getCommentRateId();

        Map<Comment, List<Comment>> commentHashMap = new LinkedHashMap<Comment, List<Comment>>();

        //1. find all top comments
        List<Comment> topCommentList = commentService.getTopCommentContentList(clickRateId);
        for (Comment comment: topCommentList) {
            int commentId = comment.getCommentId();
            List<Comment> replyCommentList = commentService.getReplyCommentList(commentId);
            commentHashMap.put(comment, replyCommentList);
        };

        int travelId = (Integer) session.getAttribute("travelId");
        Travel currentTravel = travelService.queryTravelById(travelId);
        List<Rate> rateList = rateService.queryTravelRate(travelId);
        String travelTitle = currentTravel.getTravelName() + " (" + currentTravel.getTravelCountry() + ")";
        String travelReviews = currentTravel.getTotalRateNumber() + " reviews";
        String travelScore = "";

        if (currentTravel.getTotalRateNumber() > 0) {
            Double avgScore = currentTravel.getTotalRateScore() / currentTravel.getTotalRateNumber();
            String avgScoreStr = String.format("%.2f", avgScore);
            travelScore = "average rate score: " + avgScoreStr;
        } else {
            travelScore = "This travel has no rate now!";
        }

        model.addAttribute("rateList", rateList);
        model.addAttribute("travelTitle", travelTitle);
        model.addAttribute("travelReviews", travelReviews);
        model.addAttribute("travelScore", travelScore);
        model.addAttribute("commentHashMap", commentHashMap);
        model.addAttribute("rootRateId", clickRateId);
        model.addAttribute("commentParentId", commentParentId);

        return "userTravelRate";
    }

    @RequestMapping("/addReplyForTravel")
    public String addReplyForTravel(@RequestParam("replyContent") String replyContent, @RequestParam("replyRateId") String replyRateId,
                                  @RequestParam("replyParentId") String replyParentId, HttpSession session, Model model) throws ParseException {
        System.out.println("reply content => " + replyContent);
        System.out.println("reply rate id => " + replyRateId);
        System.out.println("reply parent id => " + replyParentId);

        int commentParentId = Integer.parseInt(replyParentId);

        //if reply to a reply, find the top comment(parentId = 0)
        Comment parentComment = commentService.getCommentById(commentParentId);
        while (parentComment.getCommentParentId() != 0) {
            commentParentId = parentComment.getCommentParentId();
            parentComment = commentService.getCommentById(commentParentId);
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date commentCreateTime = sdf.parse(nowTime);

        String commentAuthor = (String)session.getAttribute("userName");
        int isDeleted = 0;

        Comment comment = new Comment(commentAuthor, replyContent, parentComment.getCommentId(), Integer.parseInt(replyRateId), commentCreateTime, isDeleted);
        commentService.addCommentForTravel(comment);
        rateService.addCommentById(Integer.parseInt(replyRateId));
        System.out.println("final parent id => " + parentComment.getCommentId());

        model.addAttribute("rateId", replyRateId);
        return "redirect:/comment/showCommentForTravel";
    }

}
