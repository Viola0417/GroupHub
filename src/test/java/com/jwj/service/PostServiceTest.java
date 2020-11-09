package com.jwj.service;

import com.jwj.pojo.Post;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class PostServiceTest {
    @Test
    public void testQueryAllMovieRootPost() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PostService postServiceImpl = (PostService) context.getBean("PostserviceImpl");
        List<Post> movieRootPostList = postServiceImpl.queryAllMovieRootPost();
        for (Post p: movieRootPostList) {
            System.out.println(p.getPostContent());
        }
    }
}
