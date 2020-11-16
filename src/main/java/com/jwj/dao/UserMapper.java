package com.jwj.dao;

import com.jwj.pojo.User;

public interface UserMapper {

    //search password by userName
    String searchPasswordByName(String userName);

    //check whether userName in user table
    int checkUserExist(String userName);

    //modify user's password according to userName
    int updateUserPassword(User user);

    //get User by user name
    User getUserbyName(String userName);

    //check whether this user is activate
    //1 means activate, 0 means deleted
    int checkUserActivated(String userName);

    //check whether email in user table
    int checkEmailExist(String email);

    //add new user
    int addUser(User user);
}
