package com.jwj.controller;

import com.jwj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminService adminService;

}
