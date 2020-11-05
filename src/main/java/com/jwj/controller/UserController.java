package com.jwj.controller;

import com.jwj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;
}
