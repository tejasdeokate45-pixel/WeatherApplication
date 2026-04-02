package com.weather.WeatherApp.dto;

public class WeatherResponse {
    private String city;
    private double temperature;
    private String condition;
    private String region;
    private String county;

    public WeatherResponse() {
    }

    public WeatherResponse(String city, String condition, String county, String region, double temperature) {
        this.city = city;
        this.condition = condition;
        this.county = county;
        this.region = region;
        this.temperature = temperature;
    }

    // ✅ CORRECT GETTERS

    public String getCity() {
        return city;
    }

    public String getCondition() {
        return condition;
    }

    public String getCounty() {
        return county;
    }

    public String getRegion() {
        return region;
    }

    public double getTemperature() {
        return temperature;
    }

    // ✅ SETTERS (already correct)

    public void setCity(String city) {
        this.city = city;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}