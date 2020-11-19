package com.jwj.service;

import com.jwj.pojo.Comment;

import java.util.List;

public interface CommentService {

    //add a comment
    public int addComment(Comment comment);

    //query comment number
    public int queryCommentByRateId(int commentRateId);

    //get all top comment
    public List<Comment> getTopCommentContentList(int commentRateId);

    //get all reply comments of top comment
    public List<Comment> getReplyCommentList(int commentParentId);


    //get comment by commentId
    public Comment getCommentById(int commentId);
}
