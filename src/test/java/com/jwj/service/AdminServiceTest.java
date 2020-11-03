package com.jwj.service;

import com.jwj.pojo.Admin;
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

    @Test
    public void testUpdatePassword() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AdminService adminServiceImpl = (AdminService) context.getBean("AdminServiceImpl");
        Admin admin = new Admin("admin", "5678");
        adminServiceImpl.updatePassword(admin);
        System.out.println("modification completed!");
    }

    @Test
    public void testAddAdmin() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AdminService adminServiceImpl = (AdminService) context.getBean("AdminServiceImpl");
        Admin admin = new Admin("Ben", "666");
        adminServiceImpl.addAdmin(admin);
        System.out.println("insertion completed!");
    }

    @Test
    public void testDeleteAddmin() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AdminService adminServiceImpl = (AdminService) context.getBean("AdminServiceImpl");
        String adminName = "Ben";
        adminServiceImpl.deleteAdminByName(adminName);
        System.out.println("deletion completed!");
    }

    @Test
    public void checkAdminExist() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AdminService adminServiceImpl = (AdminService) context.getBean("AdminServiceImpl");
        String adminName = "Ben";
        boolean res = adminServiceImpl.checkAdminExist(adminName);
        if (res) {
            System.out.println("this admin exists!");
        } else {
            System.out.println("this admin doesn't exist!");
        }
    }
}
