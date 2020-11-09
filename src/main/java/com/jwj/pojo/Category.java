package com.jwj.pojo;

public class Category {
    private int categoryId;
    private String categoryType;
    private String categoryName;
    private String description;
    private int totalRateNumber;
    private double totalRateScore;

    public Category() {
    }

    public Category(String categoryType, String categoryName, String description) {
        this.categoryType = categoryType;
        this.categoryName = categoryName;
        this.description = description;
        this.totalRateNumber = 0;
        this.totalRateScore = 0.0;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalRateNumber() {
        return totalRateNumber;
    }

    public void setTotalRateNumber(int totalRateNumber) {
        this.totalRateNumber = totalRateNumber;
    }

    public double getTotalRateScore() {
        return totalRateScore;
    }

    public void setTotalRateScore(double totalRateScore) {
        this.totalRateScore = totalRateScore;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryType='" + categoryType + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", totalRateNumber=" + totalRateNumber +
                ", totalRateScore=" + totalRateScore +
                '}';
    }
}
