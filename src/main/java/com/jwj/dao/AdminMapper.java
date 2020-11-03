package com.jwj.dao;

import com.jwj.pojo.Admin;

public interface AdminMapper {

    //search password by admin Name
    String searchPasswordByName(String adminName);

    //modify password by admin
    int updatePassword(Admin admin);

    //delete Admin by adminName
    int deleteAdminByName(String name);

    //insert Admin
    int addAdmin(Admin admin);

    //check whether adminName in admin table
    int checkAdminExist(String adminName);
}
