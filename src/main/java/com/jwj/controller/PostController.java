package com.jwj.controller;

import com.jwj.pojo.Movie;
import com.jwj.service.MovieService;
import com.jwj.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/post")
public class PostController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("PostServiceImpl")
    private PostService postService;

    @RequestMapping("/toMoviePost")
    public String toMoviePost(int movieId) {
        System.out.println("movieId => " + movieId);
        return "UserMoviePost";
    }
}
