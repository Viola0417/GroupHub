package com.jwj.service;

import com.jwj.pojo.Comment;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentServiceTest {
    @Test
    public void testAddComment() throws ParseException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentService commentServiceImpl = (CommentService) context.getBean("CommentServiceImpl");
        Date date = new Date();
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
        Comment comment = new Comment("zzx", "hahaha", 1, 1, time, 0, 0);
        commentServiceImpl.addComment(comment);
        System.out.println("comment added!");
    }

    @Test
    public void testQueryCommentByRateId() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentService commentServiceImpl = (CommentService) context.getBean("CommentServiceImpl");
        int rateId = 2;
        int res = commentServiceImpl.queryCommentByRateId(rateId);
        System.out.println(res);
    }

    @Test
    public void testGetTopCommentContentList() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentService commentServiceImpl = (CommentService) context.getBean("CommentServiceImpl");
        int rateId = 1;
        List<Comment> res = commentServiceImpl.getTopCommentContentList(rateId);
        for (Comment c: res) {
            System.out.println(c.toString());
        }
    }

    @Test
    public void testGetReplyCommentList() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentService commentServiceImpl = (CommentService) context.getBean("CommentServiceImpl");
        int parentId = 1;
        List<Comment> res = commentServiceImpl.getReplyCommentList(parentId);
        for (Comment c: res) {
            System.out.println(c.toString());
        }
    }

    @Test
    public void testGetCommentById() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentService commentServiceImpl = (CommentService) context.getBean("CommentServiceImpl");
        int id = 1;
        Comment c = commentServiceImpl.getCommentById(id);
        System.out.println(c.toString());
    }

    @Test
    public void testDeleteCommentsByRateId() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentService commentServiceImpl = (CommentService) context.getBean("CommentServiceImpl");
        int id = 38;
        commentServiceImpl.deleteCommentsByRateId(id);
        System.out.println("delete completion!");
    }
}
