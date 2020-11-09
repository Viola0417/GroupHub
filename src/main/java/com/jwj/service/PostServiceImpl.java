package com.jwj.service;

import com.jwj.dao.PostMapper;
import com.jwj.pojo.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostMapper postMapper;

    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    //query all movie root post
    public List<Post> queryAllMovieRootPost() {
        return postMapper.queryAllMovieRootPost();
    }
}
