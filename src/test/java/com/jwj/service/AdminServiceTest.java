package com.jwj.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AdminServiceTest {
    @Test
    public void testSearchPasswordByName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AdminService adminServiceImpl = (AdminService) context.getBean("AdminServiceImpl");
        String adminName = "admin";
        String adminPassword = adminServiceImpl.searchPasswordByName(adminName);
        System.out.println("admin password => " + adminPassword);
    }
}
