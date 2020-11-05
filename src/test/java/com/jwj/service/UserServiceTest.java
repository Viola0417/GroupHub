package com.jwj.service;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {
    @Test
    public void testSearchPasswordByName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl = (UserService) context.getBean("UserServiceImpl");
        String userName = "grouphuber";
        String userPassword = userServiceImpl.searchPasswordByName(userName);
        System.out.println("user password => " + userPassword);
    }
}
