package com.jwj.pojo;

public class User {
    private String userName;
    private String userPassword;
    private String email;
    //1 means this user is active, 0 means inactive
    private int status;
    private int noOfFriends;
    private int noOfGroups;

    public User() {
    }

    public User(String userName, String userPassword, String email) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.status = 1;
        this.noOfFriends = 0;
        this.noOfGroups = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNoOfFriends() {
        return noOfFriends;
    }

    public void setNoOfFriends(int noOfFriends) {
        this.noOfFriends = noOfFriends;
    }

    public int getNoOfGroups() {
        return noOfGroups;
    }

    public void setNoOfGroups(int noOfGroups) {
        this.noOfGroups = noOfGroups;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", noOfFriends=" + noOfFriends +
                ", noOfGroups=" + noOfGroups +
                '}';
    }
}
