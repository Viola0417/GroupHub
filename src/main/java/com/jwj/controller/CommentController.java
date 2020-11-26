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
        int movieIdInt = (Integer) session.getAttribute("movieId");
        String movieId = Integer.toString(movieIdInt);
        model.addAttribute("movieId", movieId);
        return "redirect:/rate/toMovieRate";
    }

    @RequestMapping("addTopComment")
    public String addTopComment(@RequestParam("commentContent") String commentContent, @RequestParam("commentRateId") String commentRateId,
                                HttpSession session, Model model) throws ParseException {
        String errorMsg = "";
        int movieIdInt = (Integer) session.getAttribute("movieId");
        int commentId = Integer.parseInt(commentRateId);
        String movieId = Integer.toString(movieIdInt);

        if (commentContent == null || commentContent.length() == 0) {
            //System.out.println("empty comment!");
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

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date commentCreateTime = sdf.parse(nowTime);

        String commentAuthor = (String)session.getAttribute("userName");
        int commentParentId = 0;
        int isDeleted = 0;
        int isEdited = 0;

        Comment comment = new Comment(commentAuthor, commentContent, commentParentId, Integer.parseInt(commentRateId), commentCreateTime, isDeleted, isEdited);
        commentService.addComment(comment);
        rateService.addCommentById(Integer.parseInt(commentRateId));

        model.addAttribute("movieId", movieId);
        model.addAttribute("rateId", commentRateId);
        return "redirect:/comment/showComment";
    }

    @RequestMapping("showComment")
    public String showComment(int rateId, Model model, HttpSession session) {
        //System.out.println("rateId => " + rateId);
        Map<Comment, List<Comment>> commentHashMap = new LinkedHashMap<Comment, List<Comment>>();

        //1. find all top comments
        List<Comment> topCommentList = commentService.getTopCommentContentList(rateId);
        for (Comment comment: topCommentList) {
            int commentId = comment.getCommentId();
            List<Comment> replyCommentList = commentService.getReplyCommentList(commentId);
            commentHashMap.put(comment, replyCommentList);
        }

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
        model.addAttribute("modifyComment", 0);
        session.setAttribute("rateId", rateId);

        return "userMovieRate";
    }

    @RequestMapping("/toAddReply")
    public String toAddReply(int commentParentId, HttpSession session, Model model) {
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
        model.addAttribute("modifyComment", 0);

        return "userMovieRate";
    }

    @RequestMapping("/addReply")
    public String addReply(@RequestParam("replyContent") String replyContent, @RequestParam("replyRateId") String replyRateId,
                           @RequestParam("replyParentId") String replyParentId, HttpSession session, Model model) throws ParseException {
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
        int isEdited = 0;

        Comment comment = new Comment(commentAuthor, replyContent, parentComment.getCommentId(), Integer.parseInt(replyRateId), commentCreateTime, isDeleted, isEdited);
        commentService.addComment(comment);
        rateService.addCommentById(Integer.parseInt(replyRateId));

        model.addAttribute("rateId", replyRateId);
        return "redirect:/comment/showComment";
    }


    @RequestMapping("/showMovieCommentToAdmin")
    public String showMovieCommentToAdmin(int rateId, Model model, HttpSession session) {
        System.out.println("show Movie Comment To Admin");
        System.out.println("rateId => " + rateId);
        Map<Comment, List<Comment>> commentHashMap = new LinkedHashMap<Comment, List<Comment>>();

        //1. find all top comments
        List<Comment> topCommentList = commentService.getTopCommentContentList(rateId);

        for (Comment comment: topCommentList) {
            int commentId = comment.getCommentId();
            List<Comment> replyCommentList = commentService.getReplyCommentList(commentId);
            commentHashMap.put(comment, replyCommentList);
        }

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

        model.addAttribute("movieRateList", rateList);
        model.addAttribute("movieTitle", movieTitle);
        model.addAttribute("movieReviews", movieReviews);
        model.addAttribute("movieScore", movieScore);
        model.addAttribute("commentHashMap", commentHashMap);
        model.addAttribute("rootRateId", rateId);

        return "adminMovieRate";
    }

    @RequestMapping("/adminDeleteMovieComment")
    public String adminDeleteMovieComment(int commentId, Model model, HttpSession session) {
        System.out.println("delete comment, comment id => " + commentId);
        Comment comment = commentService.getCommentById(commentId);
        Rate rate = rateService.queryRateById(comment.getCommentRateId());

        //if comment is top comment, delete it and all of its related comments
        if (comment.getCommentParentId() == 0) {
            //find all of its reply and delete
            List<Comment> replyList = commentService.getReplyCommentList(commentId);
            int replyNo = replyList.size();
            for (Comment reply: replyList) {
                commentService.deleteCommentById(reply.getCommentId());
            }
            //delete this top comment itself
            commentService.deleteCommentById(commentId);
            //update rate of this comment, make it total reply to be current - reply - topComment
            rate.setRateTotalReply(rate.getRateTotalReply() - replyNo - 1);
        } else {
            //if comment is just a reply, only delete this reply
            commentService.deleteCommentById(commentId);
            rate.setRateTotalReply(rate.getRateTotalReply() - 1);
        }

        rateService.updateRate(rate);
        int movieId = (Integer) session.getAttribute("movieId");
        model.addAttribute("movieId", movieId);
        return "redirect:/rate/toDeleteMovieRate";
    }

    @RequestMapping("/userDeleteMovieComment")
    public String userDeleteMovieComment(int commentId, Model model, HttpSession session) {
        Comment comment = commentService.getCommentById(commentId);
        Rate rate = rateService.queryRateById(comment.getCommentRateId());

        //if comment is top comment, delete it and all of its related comments
        if (comment.getCommentParentId() == 0) {
            //find all of its reply and delete
            List<Comment> replyList = commentService.getReplyCommentList(commentId);
            int replyNo = replyList.size();
            for (Comment reply: replyList) {
                commentService.deleteCommentById(reply.getCommentId());
            }
            //delete this top comment itself
            commentService.deleteCommentById(commentId);
            //update rate of this comment, make it total reply to be current - reply - topComment
            rate.setRateTotalReply(rate.getRateTotalReply() - replyNo - 1);
        } else {
            //if comment is just a reply, only delete this reply
            commentService.deleteCommentById(commentId);
            rate.setRateTotalReply(rate.getRateTotalReply() - 1);
        }

        rateService.updateRate(rate);
        model.addAttribute("rateId", rate.getRateId());
        return "redirect:/comment/showComment";
    }

    @RequestMapping("/userToUpdateMovieComment")
    public String userToUpdateMovieComment(int commentId, Model model, HttpSession session) {
        int rateId = (Integer) session.getAttribute("rateId");
        Map<Comment, List<Comment>> commentHashMap = new LinkedHashMap<Comment, List<Comment>>();

        //1. find all top comments
        List<Comment> topCommentList = commentService.getTopCommentContentList(rateId);
        for (Comment comment: topCommentList) {
            int curCommentId = comment.getCommentId();
            List<Comment> replyCommentList = commentService.getReplyCommentList(curCommentId);
            commentHashMap.put(comment, replyCommentList);
        }

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
        model.addAttribute("modifyComment", 1);
        model.addAttribute("commentId", commentId);

        return "userMovieRate";
    }

    @RequestMapping("/userUpdateMovieComment")
    public String userUpdateMovieComment(@RequestParam("commentContent") String commentContent, @RequestParam("commentId") String commentId,
                                         Model model) throws ParseException {
        System.out.println("user update comment!");
        System.out.println("content => " + commentContent);
        System.out.println("id => " + commentId);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date commentCreateTime = sdf.parse(nowTime);

        Comment comment = commentService.getCommentById(Integer.parseInt(commentId));
        comment.setCommentContent(commentContent);
        comment.setCommentCreateTime(commentCreateTime);
        comment.setIsEdited(1);

        commentService.updateComment(comment);

        model.addAttribute("rateId", comment.getCommentRateId());
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
        int isEdited = 0;

        Comment comment = new Comment(commentAuthor, commentContent, commentParentId, Integer.parseInt(commentRateId), commentCreateTime, isDeleted, isEdited);
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
        }

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
        model.addAttribute("modifyComment", 0);
        session.setAttribute("rateId", rateId);

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
        }

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
        model.addAttribute("modifyComment", 0);

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
        int isEdited = 0;

        Comment comment = new Comment(commentAuthor, replyContent, parentComment.getCommentId(), Integer.parseInt(replyRateId), commentCreateTime, isDeleted, isEdited);
        commentService.addCommentForBook(comment);
        rateService.addCommentById(Integer.parseInt(replyRateId));
        System.out.println("final parent id => " + parentComment.getCommentId());

        model.addAttribute("rateId", replyRateId);
        return "redirect:/comment/showCommentForBook";
    }

    @RequestMapping("/showBookCommentToAdmin")
    public String showBookCommentToAdmin(int rateId, Model model, HttpSession session) {
        System.out.println("show book Comment To Admin");
        System.out.println("rateId => " + rateId);
        Map<Comment, List<Comment>> commentHashMap = new LinkedHashMap<Comment, List<Comment>>();

        //1. find all top comments
        List<Comment> topCommentList = commentService.getTopCommentContentList(rateId);

        for (Comment comment: topCommentList) {
            int commentId = comment.getCommentId();
            List<Comment> replyCommentList = commentService.getReplyCommentList(commentId);
            commentHashMap.put(comment, replyCommentList);
        }

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
            bookScore = "This movie has no rate now!";
        }

        model.addAttribute("bookRateList", rateList);
        model.addAttribute("bookTitle",bookTitle);
        model.addAttribute("bookReviews", bookReviews);
        model.addAttribute("bookScore", bookScore);
        model.addAttribute("commentHashMap", commentHashMap);
        model.addAttribute("rootRateId", rateId);

        return "adminBookRate";
    }

    @RequestMapping("/adminDeleteBookComment")
    public String adminDeleteBookComment(int commentId, Model model, HttpSession session) {
        System.out.println("delete comment, comment id => " + commentId);
        Comment comment = commentService.getCommentById(commentId);
        Rate rate = rateService.queryRateById(comment.getCommentRateId());

        //if comment is top comment, delete it and all of its related comments
        if (comment.getCommentParentId() == 0) {
            //find all of its reply and delete
            List<Comment> replyList = commentService.getReplyCommentList(commentId);
            int replyNo = replyList.size();
            for (Comment reply: replyList) {
                commentService.deleteCommentById(reply.getCommentId());
            }
            //delete this top comment itself
            commentService.deleteCommentById(commentId);
            //update rate of this comment, make it total reply to be current - reply - topComment
            rate.setRateTotalReply(rate.getRateTotalReply() - replyNo - 1);
        } else {
            //if comment is just a reply, only delete this reply
            commentService.deleteCommentById(commentId);
            rate.setRateTotalReply(rate.getRateTotalReply() - 1);
        }

        rateService.updateRateForBook(rate);
        int bookId = (Integer) session.getAttribute("bookId");
        model.addAttribute("bookId", bookId);
        return "redirect:/rate/toDeleteBookRate";
    }

    @RequestMapping("/userDeleteBookComment")
    public String userDeleteBookComment(int commentId, Model model, HttpSession session) {
        Comment comment = commentService.getCommentById(commentId);
        Rate rate = rateService.queryRateById(comment.getCommentRateId());

        //if comment is top comment, delete it and all of its related comments
        if (comment.getCommentParentId() == 0) {
            //find all of its reply and delete
            List<Comment> replyList = commentService.getReplyCommentList(commentId);
            int replyNo = replyList.size();
            for (Comment reply: replyList) {
                commentService.deleteCommentById(reply.getCommentId());
            }
            //delete this top comment itself
            commentService.deleteCommentById(commentId);
            //update rate of this comment, make it total reply to be current - reply - topComment
            rate.setRateTotalReply(rate.getRateTotalReply() - replyNo - 1);
        } else {
            //if comment is just a reply, only delete this reply
            commentService.deleteCommentById(commentId);
            rate.setRateTotalReply(rate.getRateTotalReply() - 1);
        }

        rateService.updateRateForBook(rate);
        model.addAttribute("rateId", rate.getRateId());
        return "redirect:/comment/showCommentForBook";
    }

    @RequestMapping("/userToUpdateBookComment")
    public String userToUpdateBookComment(int commentId, Model model, HttpSession session) {
        int rateId = (Integer) session.getAttribute("rateId");
        Map<Comment, List<Comment>> commentHashMap = new LinkedHashMap<Comment, List<Comment>>();

        //1. find all top comments
        List<Comment> topCommentList = commentService.getTopCommentContentList(rateId);
        for (Comment comment: topCommentList) {
            int curCommentId = comment.getCommentId();
            List<Comment> replyCommentList = commentService.getReplyCommentList(curCommentId);
            commentHashMap.put(comment, replyCommentList);
        }

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
        model.addAttribute("modifyComment", 1);
        model.addAttribute("commentId", commentId);

        return "userBookRate";
    }

    @RequestMapping("/userUpdateBookComment")
    public String userUpdateBookComment(@RequestParam("commentContent") String commentContent, @RequestParam("commentId") String commentId,
                                         Model model) throws ParseException {
        System.out.println("user update comment!");
        System.out.println("content => " + commentContent);
        System.out.println("id => " + commentId);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date commentCreateTime = sdf.parse(nowTime);

        Comment comment = commentService.getCommentById(Integer.parseInt(commentId));
        comment.setCommentContent(commentContent);
        comment.setCommentCreateTime(commentCreateTime);
        comment.setIsEdited(1);

        commentService.updateComment(comment);

        model.addAttribute("rateId", comment.getCommentRateId());
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
        int isEdited = 0;

        Comment comment = new Comment(commentAuthor, commentContent, commentParentId, Integer.parseInt(commentRateId), commentCreateTime, isDeleted, isEdited);
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
        model.addAttribute("modifyComment", 0);
        session.setAttribute("rateId", rateId);

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
        model.addAttribute("modifyComment", 0);

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
        int isEdited = 0;

        Comment comment = new Comment(commentAuthor, replyContent, parentComment.getCommentId(), Integer.parseInt(replyRateId), commentCreateTime, isDeleted, isEdited);
        commentService.addCommentForTravel(comment);
        rateService.addCommentById(Integer.parseInt(replyRateId));
        System.out.println("final parent id => " + parentComment.getCommentId());

        model.addAttribute("rateId", replyRateId);
        return "redirect:/comment/showCommentForTravel";
    }

    @RequestMapping("/showTravelCommentToAdmin")
    public String showTravelCommentToAdmin(int rateId, Model model, HttpSession session) {
        System.out.println("show travel Comment To Admin");
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

        model.addAttribute("travelRateList", rateList);
        model.addAttribute("travelTitle", travelTitle);
        model.addAttribute("travelReviews", travelReviews);
        model.addAttribute("travelScore", travelScore);
        model.addAttribute("commentHashMap", commentHashMap);
        model.addAttribute("rootRateId", rateId);

        return "adminTravelRate";
    }

    @RequestMapping("/adminDeleteTravelComment")
    public String adminDeleteTravelComment(int commentId, Model model, HttpSession session) {
        System.out.println("delete comment, comment id => " + commentId);
        Comment comment = commentService.getCommentById(commentId);
        Rate rate = rateService.queryRateById(comment.getCommentRateId());

        //if comment is top comment, delete it and all of its related comments
        if (comment.getCommentParentId() == 0) {
            //find all of its reply and delete
            List<Comment> replyList = commentService.getReplyCommentList(commentId);
            int replyNo = replyList.size();
            for (Comment reply: replyList) {
                commentService.deleteCommentById(reply.getCommentId());
            }
            //delete this top comment itself
            commentService.deleteCommentById(commentId);
            //update rate of this comment, make it total reply to be current - reply - topComment
            rate.setRateTotalReply(rate.getRateTotalReply() - replyNo - 1);
        } else {
            //if comment is just a reply, only delete this reply
            commentService.deleteCommentById(commentId);
            rate.setRateTotalReply(rate.getRateTotalReply() - 1);
        }

        rateService.updateRateForTravel(rate);
        int travelId = (Integer) session.getAttribute("travelId");
        model.addAttribute("travelId", travelId);
        return "redirect:/rate/toDeleteTravelRate";
    }

    @RequestMapping("/userDeleteTravelComment")
    public String userDeleteTravelComment(int commentId, Model model, HttpSession session) {
        Comment comment = commentService.getCommentById(commentId);
        Rate rate = rateService.queryRateById(comment.getCommentRateId());

        //if comment is top comment, delete it and all of its related comments
        if (comment.getCommentParentId() == 0) {
            //find all of its reply and delete
            List<Comment> replyList = commentService.getReplyCommentList(commentId);
            int replyNo = replyList.size();
            for (Comment reply: replyList) {
                commentService.deleteCommentById(reply.getCommentId());
            }
            //delete this top comment itself
            commentService.deleteCommentById(commentId);
            //update rate of this comment, make it total reply to be current - reply - topComment
            rate.setRateTotalReply(rate.getRateTotalReply() - replyNo - 1);
        } else {
            //if comment is just a reply, only delete this reply
            commentService.deleteCommentById(commentId);
            rate.setRateTotalReply(rate.getRateTotalReply() - 1);
        }

        rateService.updateRateForTravel(rate);
        model.addAttribute("rateId", rate.getRateId());
        return "redirect:/comment/showCommentForTravel";
    }

    @RequestMapping("/userToUpdateTravelComment")
    public String userToUpdateTravelComment(int commentId, Model model, HttpSession session) {
        int rateId = (Integer) session.getAttribute("rateId");
        Map<Comment, List<Comment>> commentHashMap = new LinkedHashMap<Comment, List<Comment>>();

        //1. find all top comments
        List<Comment> topCommentList = commentService.getTopCommentContentList(rateId);
        for (Comment comment: topCommentList) {
            int curCommentId = comment.getCommentId();
            List<Comment> replyCommentList = commentService.getReplyCommentList(curCommentId);
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
        model.addAttribute("modifyComment", 1);
        model.addAttribute("commentId", commentId);

        return "userTravelRate";
    }

    @RequestMapping("/userUpdateTravelComment")
    public String userUpdateTravelComment(@RequestParam("commentContent") String commentContent, @RequestParam("commentId") String commentId,
                                        Model model) throws ParseException {
        System.out.println("user update comment!");
        System.out.println("content => " + commentContent);
        System.out.println("id => " + commentId);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date commentCreateTime = sdf.parse(nowTime);

        Comment comment = commentService.getCommentById(Integer.parseInt(commentId));
        comment.setCommentContent(commentContent);
        comment.setCommentCreateTime(commentCreateTime);
        comment.setIsEdited(1);

        commentService.updateComment(comment);

        model.addAttribute("rateId", comment.getCommentRateId());
        return "redirect:/comment/showCommentForTravel";
    }
}
