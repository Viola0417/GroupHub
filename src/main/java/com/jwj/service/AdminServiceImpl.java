package com.jwj.service;

import com.jwj.dao.AdminMapper;
import com.jwj.pojo.Admin;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    private AdminMapper adminMapper;

    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    //search password by admin Name
    public String searchPasswordByName(String adminName) {
        return adminMapper.searchPasswordByName(adminName);
    }

    //modify password by admin, search password
    public int updatePassword(Admin admin) {
        return adminMapper.updatePassword(admin);
    }

    //delete Admin by adminName
    public int deleteAdminByName(String name) {
        return adminMapper.deleteAdminByName(name);
    }

    //insert Admin
    public int addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin);
    }

    //check whether adminName in admin table
    public boolean checkAdminExist(String adminName) {
        return adminMapper.checkAdminExist(adminName) > 0;
    }
}
