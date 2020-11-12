package com.jwj.pojo;

import java.util.Date;

public class Rate {
    private int rateId;
    private String rateAuthor;
    private String rateTitle;
    private double rateScore;
    private Date rateCreateTime;
    private String rateContent;
    //categoryType: 1 -> movie, 2 -> book, 3 -> travel
    private int rateCategoryType;
    private int rateCategoryId;
    private int rateTotalLike;
    private int rateTotalReply;

    public Rate() {
    }

    public Rate(String rateAuthor, int rateCategoryType, int rateCategoryId) {
        this.rateAuthor = rateAuthor;
        this.rateCategoryType = rateCategoryType;
        this.rateCategoryId = rateCategoryId;
    }

    public Rate(String rateTitle, double rateScore, String rateContent) {
        this.rateTitle = rateTitle;
        this.rateScore = rateScore;
        this.rateContent = rateContent;
    }

    public Rate(int rateId, String rateTitle, double rateScore, Date rateCreateTime, String rateContent) {
        this.rateId = rateId;
        this.rateTitle = rateTitle;
        this.rateScore = rateScore;
        this.rateCreateTime = rateCreateTime;
        this.rateContent = rateContent;
    }

    public Rate(String rateAuthor, String rateTitle, double rateScore, Date rateCreateTime, String rateContent,
                int rateCategoryType, int rateCategoryId, int rateTotalLike, int rateTotalReply) {
        this.rateAuthor = rateAuthor;
        this.rateTitle = rateTitle;
        this.rateScore = rateScore;
        this.rateCreateTime = rateCreateTime;
        this.rateContent = rateContent;
        this.rateCategoryType = rateCategoryType;
        this.rateCategoryId = rateCategoryId;
        this.rateTotalLike = rateTotalLike;
        this.rateTotalReply = rateTotalReply;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public String getRateAuthor() {
        return rateAuthor;
    }

    public void setRateAuthor(String rateAuthor) {
        this.rateAuthor = rateAuthor;
    }

    public String getRateTitle() {
        return rateTitle;
    }

    public void setRateTitle(String rateTitle) {
        this.rateTitle = rateTitle;
    }

    public double getRateScore() {
        return rateScore;
    }

    public void setRateScore(double rateScore) {
        this.rateScore = rateScore;
    }

    public Date getRateCreateTime() {
        return rateCreateTime;
    }

    public void setRateCreateTime(Date rateCreateTime) {
        this.rateCreateTime = rateCreateTime;
    }

    public String getRateContent() {
        return rateContent;
    }

    public void setRateContent(String rateContent) {
        this.rateContent = rateContent;
    }

    public int getRateCategoryType() {
        return rateCategoryType;
    }

    public void setRateCategoryType(int rateCategoryType) {
        this.rateCategoryType = rateCategoryType;
    }

    public int getRateCategoryId() {
        return rateCategoryId;
    }

    public void setRateCategoryId(int rateCategoryId) {
        this.rateCategoryId = rateCategoryId;
    }

    public int getRateTotalLike() {
        return rateTotalLike;
    }

    public void setRateTotalLike(int rateTotalLike) {
        this.rateTotalLike = rateTotalLike;
    }

    public int getRateTotalReply() {
        return rateTotalReply;
    }

    public void setRateTotalReply(int rateTotalReply) {
        this.rateTotalReply = rateTotalReply;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "rateId=" + rateId +
                ", rateAuthor='" + rateAuthor + '\'' +
                ", rateTitle='" + rateTitle + '\'' +
                ", rateScore=" + rateScore +
                ", rateCreateTime=" + rateCreateTime +
                ", rateContent='" + rateContent + '\'' +
                ", rateCategoryType=" + rateCategoryType +
                ", rateCategoryId=" + rateCategoryId +
                ", rateTotalLike=" + rateTotalLike +
                ", rateTotalReply=" + rateTotalReply +
                '}';
    }
}
