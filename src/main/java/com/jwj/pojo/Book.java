package com.jwj.pojo;

public class Book {
    private int bookId;
    private String bookName;
    private String bookAuthor;
    private String description;
    private int totalRateNumber;
    private double totalRateScore;

    public Book() {

    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
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
}
