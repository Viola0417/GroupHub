package com.jwj.service;

import com.jwj.dao.UserMapper;

public class UserServiceImpl implements UserService{
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //search password by userName
    public String searchPasswordByName(String adminName) {
        return userMapper.searchPasswordByName(adminName);
    }
}
