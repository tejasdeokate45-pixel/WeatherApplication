package com.weather.WeatherApp.dto;

import java.util.ArrayList;

public class Forecast{
    public ArrayList<Forecastday> forecastday;

    public Forecast() {
    }

    public Forecast(ArrayList<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }

    public ArrayList<Forecastday> getForecastday() {
        return forecastday;
    }

    public void setForecastday(ArrayList<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }
}
