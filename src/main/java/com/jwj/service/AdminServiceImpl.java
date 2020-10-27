package com.jwj.service;

import com.jwj.dao.AdminMapper;
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
}
