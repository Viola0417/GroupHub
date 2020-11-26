package com.jwj.pojo;

import java.util.Date;

public class Request {
    private int requestId;
    private String requestAuthor;
    // movie -> 1, book -> 2, travel -> 3
    private int requestCategoryId;
    private Date requestTime;
    private String requestTitle;
    private String requestDescription;
    private int isResolved;

    public Request() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getRequestAuthor() {
        return requestAuthor;
    }

    public void setRequestAuthor(String requestAuthor) {
        this.requestAuthor = requestAuthor;
    }

    public int getRequestCategoryId() {
        return requestCategoryId;
    }

    public void setRequestCategoryId(int requestCategoryId) {
        this.requestCategoryId = requestCategoryId;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public int getIsResolved() {
        return isResolved;
    }

    public void setIsResolved(int isResolved) {
        this.isResolved = isResolved;
    }
}
