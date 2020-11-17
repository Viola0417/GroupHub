package com.jwj.service;

import com.jwj.pojo.Rate;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RateServiceTest {
    @Test
    public void testQuerySpecRate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RateService rateServiceImpl = (RateService) context.getBean("RateServiceImpl");
        int id = 1;
        List<Rate> rateList = rateServiceImpl.queryMovieRate(id);
        for (Rate r: rateList) {
            System.out.println(r.toString());
        }
    }

    @Test
    public void testAddRate() throws ParseException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RateService rateServiceImpl = (RateService) context.getBean("RateServiceImpl");
        Date date = new Date();
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
        Rate rate = new Rate("Ben", "One of the Best Movies I'll never watch twice", 4.0, time, "The Revenant falls into the same category as Aronofsky's \"Requiem for a Dream\" and Spielberg's \"Schindler's List\" for me, in the essence of being a terrific movie but not something I think I can sit through a second time. In all these movies there are brutal sequences that cause emotional stress and disgust just watching because it is so REAL and GRIPPING. To think that events in these movies actually happened or that they could very well happen is too much for me to think about. Not to say that I did not enjoy this film thoroughly.",
                1, 3, 0, 0);
        rateServiceImpl.addRate(rate);
        System.out.println("add completion!");
    }

    @Test
    public void testQueryMovieRateByAuthor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RateService rateServiceImpl = (RateService) context.getBean("RateServiceImpl");
        Rate rate = new Rate("grouphuber", 1, 2);
        List<Rate> rateList = rateServiceImpl.queryMovieRateByAuthor(rate);
        if (rateList.isEmpty()) {
            System.out.println("this user has no rate on this film!");
        }
        for (Rate r: rateList) {
            System.out.println(r.toString());
        }
    }

    @Test
    public void testQueryRateById() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RateService rateServiceImpl = (RateService) context.getBean("RateServiceImpl");
        int id = 15;
        Rate r = rateServiceImpl.queryRateById(id);
        System.out.println(r.toString());
    }

    @Test
    public void testUpdateRate() throws ParseException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RateService rateServiceImpl = (RateService) context.getBean("RateServiceImpl");
        int id = 15;
        Date date = new Date();
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse(nowTime);
        Rate newRate = new Rate(id, "modified title", 5.0, time,"modified content");
        rateServiceImpl.updateRate(newRate);
        System.out.println(rateServiceImpl.queryRateById(id).toString());
    }

    @Test
    public void testDeleteRate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RateService rateServiceImpl = (RateService) context.getBean("RateServiceImpl");
        int id = 23;
        rateServiceImpl.deleteRateById(id);
        System.out.println("delete completion!");
    }
}
