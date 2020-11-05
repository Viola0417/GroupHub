package com.jwj.pojo;

public class Category {
    private int categoryId;
    private String categoryName;
    private String description;
    private double ratings;

    public Category() {}

    public Category(int categoryId, String categoryName, String description, double ratings) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.ratings = ratings;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }
}
