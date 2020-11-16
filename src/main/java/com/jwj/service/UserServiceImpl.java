package com.jwj.service;

import com.jwj.dao.UserMapper;
import com.jwj.pojo.User;

public class UserServiceImpl implements UserService{
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //search password by userName
    public String searchPasswordByName(String userName) {
        return userMapper.searchPasswordByName(userName);
    }

    //check whether userName in user table
    public boolean checkUserExist(String userName) {
        return userMapper.checkUserExist(userName) > 0;
    }

    //modify user's password according to userName
    public int updateUserPassword(User user) {
        return userMapper.updateUserPassword(user);
    }

    //get User by user name
    public User getUserbyName(String userName) {
        return userMapper.getUserbyName(userName);
    }

    //check whether this user is activate
    //1 means activate, 0 means deleted
    public boolean checkUserActivated(String userName) {
        return userMapper.checkUserActivated(userName) == 1;
    }

    //check whether email in user table
    public boolean checkEmailExist(String email) {
        return userMapper.checkEmailExist(email) > 0;
    }

    //add new user
    public int addUser(User user) {
        return userMapper.addUser(user);
    }
}
