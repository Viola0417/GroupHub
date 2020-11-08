package com.jwj.service;

import com.jwj.pojo.User;

public interface UserService {
    //search password by userName
    String searchPasswordByName(String adminName);

    //check whether userName exists
    boolean checkUserExist(String userName);

    //modify user's password according to userName
    int updateUserPassword(User user);

    //get User by user name
    User getUserbyName(String userName);

    //check whether this user is activate
    //1 means activate, 0 means deleted
    boolean checkUserActivated(String userName);
}
