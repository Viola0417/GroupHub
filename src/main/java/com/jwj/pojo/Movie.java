package com.jwj.pojo;

public class Movie {
    private int movieId;
    private String movieName;
    private int movieYear;
    private String description;
    private int totalRateNumber;
    private double totalRateScore;

    public Movie() {
    }

    public Movie(String movieName, int movieYear, String description) {
        this.movieName = movieName;
        this.movieYear = movieYear;
        this.description = description;
        this.totalRateNumber = 0;
        this.totalRateScore = 0.0;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
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
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieYear=" + movieYear +
                ", description='" + description + '\'' +
                ", totalRateNumber=" + totalRateNumber +
                ", totalRateScore=" + totalRateScore +
                '}';
    }
}
