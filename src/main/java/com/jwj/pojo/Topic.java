package com.jwj.pojo;

public class Topic {
    private int topicId;
    private int noOfPosts;
    private String topicContent;
    private String createdTime;
    private double ratings;
    private int userId;
    private int categoryId;

    public Topic() {

    }

    public Topic(int topicId, int noOfPosts, String topicContent, String createdTime, double ratings, int userId, int categoryId) {
        this.topicId = topicId;
        this.noOfPosts = noOfPosts;
        this.topicContent = topicContent;
        this.createdTime = createdTime;
        this.ratings = ratings;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getNoOfPosts() {
        return noOfPosts;
    }

    public void setNoOfPosts(int noOfPosts) {
        this.noOfPosts = noOfPosts;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
