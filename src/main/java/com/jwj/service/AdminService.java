package com.jwj.service;

import com.jwj.pojo.Admin;

public interface AdminService {

    //search password by admin Name
    String searchPasswordByName(String adminName);

    //modify password by admin, search password
    int updatePassword(Admin admin);

    //delete Admin by adminName
    int deleteAdminByName(String name);

    //insert Admin
    int addAdmin(Admin admin);

    //check whether adminName in admin table
    boolean checkAdminExist(String adminName);
}
