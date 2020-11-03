package com.jwj.controller;

import com.jwj.pojo.Admin;
import com.jwj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminService adminService;

    //validate whether adminName exists, whether adminName and adminPassword matches
    @RequestMapping("/login")
    public String validateLogin(Admin admin, Model model) {
        String adminName = admin.getAdminName();
        String adminPassword = admin.getAdminPassword();
        System.out.println("Admin Log in: adminName => " + adminName + ", adminPassword => " + adminPassword);
        String msg = "";
        if (!adminService.checkAdminExist(adminName)) {
            msg = "This admin hasn't been delegated!";
            model.addAttribute("error", msg);
            return "adminLogin";
        }
        if (!adminService.searchPasswordByName(adminName).equals(adminPassword)) {
            msg = "Admin username and password doesn't match!";
            model.addAttribute("error", msg);
            return "adminLogin";
        }
        return "adminFunction";
    }

    @RequestMapping("/toAdminLogIn")
    public String toAdminLogIn() {
        return "adminLogin";
    }

}
