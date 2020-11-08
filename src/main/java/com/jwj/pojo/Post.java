package com.jwj.pojo;

public class Post {
    private int postId;
    private int categoryId;
    private int rootId;
    private String postContent;
    private String createdTime;
    private int previousPostId;
    private double ratingScore;
    private int totalLike;
    private int totalReply;

    public Post() {
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public int getPreviousPostId() {
        return previousPostId;
    }

    public void setPreviousPostId(int previousPostId) {
        this.previousPostId = previousPostId;
    }

    public double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(double ratingScore) {
        this.ratingScore = ratingScore;
    }

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

    public int getTotalReply() {
        return totalReply;
    }

    public void setTotalReply(int totalReply) {
        this.totalReply = totalReply;
    }
}
