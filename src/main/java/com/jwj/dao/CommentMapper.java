package com.jwj.dao;

import com.jwj.pojo.Comment;

import java.util.List;

public interface CommentMapper {

    //add a comment
    int addComment(Comment comment);
    int addCommentForBook(Comment comment);
    int addCommentForTravel(Comment comment);

    //query comment number
    int queryCommentByRateId(int commentRateId);

    //get all top comments
    List<Comment> getTopCommentList(int commentRateId);

    //get all reply comments of top comment
    List<Comment> getReplyCommentList(int commentParentId);

    //get comment by commentId
    Comment getCommentById(int commentId);

    //delete all comments of a rate
    int deleteCommentsByRateId(int commentRateId);

    //delete comment by its id
    int deleteCommentById(int commentId);

    //update comment
    int updateComment(Comment comment);
}
