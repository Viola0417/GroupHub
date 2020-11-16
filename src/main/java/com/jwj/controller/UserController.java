package com.jwj.controller;

import com.jwj.pojo.User;
import com.jwj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    //validate whether userName exists, whether userName and userPassword matches
    @RequestMapping("/login")
    public String validateLogin(HttpSession session, User user, Model model) {
        String userName = user.getUserName();
        String userPassword = user.getUserPassword();
        session.setAttribute("userName", userName);
        System.out.println("user Log in: userName => " + userName + ", userPassword => " + userPassword);
        String msg = "";
        if (!userService.checkUserExist(userName)) {
            msg = "You have not signed up! please sign up first!";
            model.addAttribute("error", msg);
            return "userIndex";
        }
        if (!userService.checkUserActivated(userName)) {
            msg = "This user has been deleted!";
            model.addAttribute("error", msg);
            return "userIndex";
        }
        if (!userService.searchPasswordByName(userName).equals(userPassword)) {
            msg = "username and password doesn't match!";
            model.addAttribute("error", msg);
            return "userIndex";
        }
        return "userFunction";
    }

    @RequestMapping("/toUserModifyPassword")
    public String toUserModifyPassword() {
        return "userModifyPassword";
    }

    //modify user's password
    @RequestMapping("/modifyPassword")
    public String modifyPassword(@RequestParam("originPassword") String originPassword,
                                 @RequestParam("newPassword") String newPassword, Model model, HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        System.out.println("User modify password: userName => " + userName + " ,originPassword => " + originPassword + ", newPassword => " + newPassword);
        String msg = "";
        String returnMsg = "";
        System.out.println("session userName is => " + session.getAttribute("userName"));
        if (!userService.searchPasswordByName(userName).equals(originPassword)) {
            msg = "The entered origin password is wrong!";
            model.addAttribute("modifyError", msg);
            return "userModifyPassword";
        }
        User user = userService.getUserbyName(userName);
        user.setUserPassword(newPassword);
        userService.updateUserPassword(user);
        returnMsg = "Successfully modify password, return";
        model.addAttribute("returnMsg",returnMsg);
        return "userModifyPassword";
    }

    @RequestMapping("/toUserFunction")
    public String toUserFunction() {
        return "userFunction";
    }

    @RequestMapping("/toUserSignUp")
    public String toUserSignUp() {
        return "userSignUp";
    }

    @RequestMapping("signUp")
    public String signUp(User user, Model model) {
        String userName = user.getUserName();
        String userPassword = user.getUserPassword();
        String userEmail = user.getEmail();
        System.out.println("userName => " + userName);
        System.out.println("userPassword => " + userPassword);
        System.out.println("userEmail => " + userEmail);
        String errorMsg = "";
        if (userService.checkUserExist(userName)) {
            errorMsg = "This username has been registered!";
            model.addAttribute("errorMsg", errorMsg);
            return "userSignUp";
        } else if (userService.checkEmailExist(userEmail)) {
            errorMsg = "This email address has been registered!";
            model.addAttribute("errorMsg", errorMsg);
            return "userSignUp";
        }
        userService.addUser(new User(userName, userPassword, userEmail));
        model.addAttribute("successMsg", "Sign up successfully! Click to sign in.");
        return "userSignUp";
    }

    @RequestMapping("/toUserIndex")
    public String toUserIndex() {
        return "userIndex";
    }
}
