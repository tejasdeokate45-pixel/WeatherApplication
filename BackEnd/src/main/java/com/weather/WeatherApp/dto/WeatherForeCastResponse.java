package com.weather.WeatherApp.dto;

import java.util.ArrayList;
import java.util.List;

public class WeatherForeCastResponse {
    private WeatherResponse weatherResponse;
    private List<DayTemp> daysTemp;

    public WeatherForeCastResponse(List<DayTemp> daysTemp, WeatherResponse weatherResponse) {
        this.daysTemp = daysTemp;
        this.weatherResponse = weatherResponse;
    }

    public WeatherForeCastResponse() {
    }

    public List<DayTemp> getDaysTemp() {
        return daysTemp;
    }

    public void setDaysTemp(List<DayTemp> daysTemp) {
        this.daysTemp = daysTemp;
    }

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public void setWeatherResponse(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }

    public void setDayTemp(List<DayTemp> dayList) {
        this.daysTemp = dayList;
    }
}
