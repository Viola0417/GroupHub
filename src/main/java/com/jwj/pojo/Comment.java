package com.jwj.pojo;

import java.util.Date;

public class Comment {
    private int commentId;
    private String commentAuthor;
    private String commentContent;
    private int commentParentId;
    private int commentRateId;
    private Date commentCreateTime;
    private int isDeleted;

    public Comment() {
    }

    public Comment(String commentAuthor, String commentContent, int commentParentId, int commentRateId, Date commentCreateTime, int isDeleted) {
        this.commentAuthor = commentAuthor;
        this.commentContent = commentContent;
        this.commentParentId = commentParentId;
        this.commentRateId = commentRateId;
        this.commentCreateTime = commentCreateTime;
        this.isDeleted = isDeleted;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentParentId() {
        return commentParentId;
    }

    public void setCommentParentId(int commentParentId) {
        this.commentParentId = commentParentId;
    }

    public int getCommentRateId() {
        return commentRateId;
    }

    public void setCommentRateId(int commentRateId) {
        this.commentRateId = commentRateId;
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentAuthor='" + commentAuthor + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", commentParentId=" + commentParentId +
                ", commentRateId=" + commentRateId +
                ", commentCreateTime=" + commentCreateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
