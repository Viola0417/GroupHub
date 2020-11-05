package com.jwj.pojo;

public class Post {
    private int postId;
    private String postContent;
    private String createdTime;
    private int parentId;
    private int topicId;

    public Post() {

    }

    public Post(int postId, String postContent, String createdTime, int parentId, int topicId) {
        this.postId = postId;
        this.postContent = postContent;
        this.createdTime = createdTime;
        this.parentId = parentId;
        this.topicId = topicId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}
