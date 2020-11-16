package com.jwj.service;

import com.jwj.pojo.User;
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

    @Test
    public void testUpdateUserPassword() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl = (UserService) context.getBean("UserServiceImpl");
        String userName = "grouphuber";
        String newPassword = "666";
        User user = userServiceImpl.getUserbyName(userName);
        System.out.println(user.toString());
        user.setUserPassword(newPassword);
        userServiceImpl.updateUserPassword(user);
        System.out.println("Password Modification Completion!");
    }

    @Test
    public void testGetUserbyName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl = (UserService) context.getBean("UserServiceImpl");
        String userName = "grouphuber";
        User user = userServiceImpl.getUserbyName(userName);
        System.out.println(user.toString());
    }

    @Test
    public void testCheckUserActivated() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl = (UserService) context.getBean("UserServiceImpl");
        String userName = "grouphuber";
        boolean res = userServiceImpl.checkUserActivated(userName);
        if (res) {
            System.out.println("This user is activated!");
        } else {
            System.out.println("This user has been deleted!");
        }
    }

    @Test
    public void testCheckEmailExist() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl = (UserService) context.getBean("UserServiceImpl");
        String email = "ben1@uci.edu";
        boolean res = userServiceImpl.checkEmailExist(email);
        if (res) {
            System.out.println("This email has been registered!");
        } else {
            System.out.println("This email has not been registered!");
        }
    }

    @Test
    public void testAddUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl = (UserService) context.getBean("UserServiceImpl");
        User user = new User("jly", "970417", "luyaoj2@uci.edu");
        userServiceImpl.addUser(user);
        System.out.println("add completed!");
    }
}
