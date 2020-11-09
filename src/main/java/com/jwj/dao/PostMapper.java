package com.jwj.dao;

import com.jwj.pojo.Post;

import java.util.List;

public interface PostMapper {

    //query all movie root post
    List<Post> queryAllMovieRootPost();
}
