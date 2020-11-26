package com.jwj.service;

import com.jwj.pojo.Request;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RequestServiceTest {
    @Test
    public void testAddRequest() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RequestService requestServiceImpl = (RequestService) context.getBean("RequestServiceImpl");
        Request request = new Request("Ben", 1, time, "title", "description");
        requestServiceImpl.addRequest(request);
        System.out.println("add completion!");
    }

    @Test
    public void testQueryRequestByAuthor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RequestService requestServiceImpl = (RequestService) context.getBean("RequestServiceImpl");
        List<Request> requestList = requestServiceImpl.queryRequestByAuthor("Ben");
        for (Request r: requestList) {
            System.out.println(r.toString());
        }
    }

    @Test
    public void testCountUnresolvedRequest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RequestService requestServiceImpl = (RequestService) context.getBean("RequestServiceImpl");
        int res = requestServiceImpl.countUnresolvedRequest();
        System.out.println(res);
    }

    @Test
    public void testQueryUnresolvedRequest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RequestService requestServiceImpl = (RequestService) context.getBean("RequestServiceImpl");
        List<Request> requestList = requestServiceImpl.queryUnresolvedRequest();
        for (Request r: requestList) {
            System.out.println(r.toString());
        }
    }
}
