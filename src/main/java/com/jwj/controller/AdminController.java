package com.jwj.controller;

import com.jwj.pojo.Admin;
import com.jwj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminService adminService;

    //validate whether adminName exists, whether adminName and adminPassword matches
    @RequestMapping("/login")
    public String validateLogin(HttpSession session, Admin admin, Model model) {
        String adminName = admin.getAdminName();
        String adminPassword = admin.getAdminPassword();
        session.setAttribute("adminName", adminName);
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
        model.addAttribute("currentAdminName", adminName);
        return "adminFunction";
    }

    //from user index page to admin log in page
    @RequestMapping("/toAdminLogIn")
    public String toAdminLogIn() {
        return "adminLogin";
    }

    //modify admin's password
    @RequestMapping("/modifyPassword")
    public String modifyPassword(@RequestParam("originPassword") String originPassword,
                                 @RequestParam("newPassword") String newPassword, Model model, HttpSession session) {
        String adminName = (String) session.getAttribute("adminName");
        System.out.println("Admin modify password: adminName => " + adminName + " ,originPassword => " + originPassword + ", newPassword => " + newPassword);
        String msg = "";
        String returnMsg = "";
        System.out.println("session adminName is => " + session.getAttribute("adminName"));
        if (!adminService.searchPasswordByName(adminName).equals(originPassword)) {
            msg = "The entered origin password is wrong!";
            model.addAttribute("modifyError", msg);
            return "adminModifyPassword";
        }
        adminService.updatePassword(new Admin(adminName, newPassword));
        returnMsg = "Successfully modify password, return";
        model.addAttribute("returnMsg",returnMsg);
        model.addAttribute("currentAdminName", adminName);
        return "adminModifyPassword";
    }

    @RequestMapping("/toAdminModifyPassword")
    public String toAdminModifyPassword() {
        return "adminModifyPassword";
    }

    @RequestMapping("/toAdminFunction")
    public String toAdminFunction() {
        return "adminFunction";
    }

    @RequestMapping("/toAdminAddGroup")
    public String toAdminAddGroup() {
        return "adminAddGroup";
    }

    @RequestMapping("/toBook")
    public String toBook() {
        return "adminBook";
    }

    @RequestMapping("/toTravel")
    public String toTravel() {
        return "adminTravel";
    }
}
