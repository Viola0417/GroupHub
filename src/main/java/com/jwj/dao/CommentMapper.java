package com.jwj.dao;

import com.jwj.pojo.Comment;

import java.util.List;

public interface CommentMapper {

    //add a comment
    int addComment(Comment comment);

    //query comment number
    int queryCommentByRateId(int commentRateId);

    //get all top comments
    List<Comment> getTopCommentList(int commentRateId);

    //get all reply comments of top comment
    List<Comment> getReplyCommentList(int commentParentId);

    //get comment by commentId
    Comment getCommentById(int commentId);
}
