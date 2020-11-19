package com.jwj.service;

import com.jwj.dao.CommentMapper;
import com.jwj.pojo.Comment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentMapper commentMapper;

    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    //add a comment
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    //query comment number
    public int queryCommentByRateId(int commentRateId) {
        return commentMapper.queryCommentByRateId(commentRateId);
    }

    //get all top comments' content
    public List<Comment> getTopCommentContentList(int commentRateId) {
        return commentMapper.getTopCommentList(commentRateId);
    }

    //get all reply comments of top comment
    public List<Comment> getReplyCommentList(int commentParentId) {
        return commentMapper.getReplyCommentList(commentParentId);
    }

    //get comment by commentId
    public Comment getCommentById(int commentId) {
        return commentMapper.getCommentById(commentId);
    }
}
