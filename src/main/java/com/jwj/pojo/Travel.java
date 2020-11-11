package com.jwj.pojo;

public class Travel {
    private int travelId;
    private String travelName;
    private String travelCountry;
    private String description;
    private int totalRateNumber;
    private double totalRateScore;

    public Travel(String travelName, String travelCountry, String description) {
        this.travelName = travelName;
        this.travelCountry = travelCountry;
        this.description = description;
        this.totalRateNumber = 0;
        this.totalRateScore = 0.0;
    }

    public int getTravelId() {
        return travelId;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
    }

    public String getTravelName() {
        return travelName;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public String getTravelCountry() {
        return travelCountry;
    }

    public void setTravelCountry(String travelCountry) {
        this.travelCountry = travelCountry;
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
