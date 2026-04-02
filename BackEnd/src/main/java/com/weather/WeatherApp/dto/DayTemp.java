package com.weather.WeatherApp.dto;

public class DayTemp {
    private String Date;
    private double minTemp;
    private double maxTemp;
    private double avgTemp;

    public DayTemp(double avgTemp, String date, double maxTemp, double minTemp) {
        this.avgTemp = avgTemp;
        Date = date;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public DayTemp() {
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }
}
