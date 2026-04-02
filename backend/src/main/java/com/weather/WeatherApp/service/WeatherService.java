package com.weather.WeatherApp.service;

import com.weather.WeatherApp.dto.*;
import io.micrometer.observation.annotation.ObservationKeyValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.forecast.url}")
    private String apiForecastUrl;

    private RestTemplate template = new RestTemplate();
    public WeatherResponse getData(String city){
        String url = apiUrl+"?key="+apiKey+"&q="+city;
        Root response = template.getForObject(url,Root.class);
        WeatherResponse weatherResponse = new WeatherResponse();

        weatherResponse.setCity(response.getLocation().name);
        weatherResponse.setCounty(response.getLocation().country);
        weatherResponse.setTemperature(response.getCurrent().temp_c);
        weatherResponse.setRegion(response.getLocation().region);
        String Cond = response.getCurrent().getCondition().text;
        weatherResponse.setCondition(Cond);

        return weatherResponse;

    }

    public WeatherForeCastResponse getForecast(String city,int days){
        WeatherForeCastResponse weatherForeCast=new WeatherForeCastResponse();
        WeatherResponse weatherResponse = getData(city);
        WeatherForeCastResponse response = new WeatherForeCastResponse();
        response.setWeatherResponse(weatherResponse);

        List<DayTemp> dayList = new ArrayList<>();
        String url = apiForecastUrl +"?key="+apiKey+"&q="+city+"&days="+days;
        Root apiResponse = template.getForObject(url, Root.class);
        Forecast forecast = apiResponse.getForecast();
        ArrayList<Forecastday> forecastday = forecast.getForecastday();
        for(Forecastday rs: forecastday)
        {
            DayTemp d=new DayTemp();
            d.setDate(rs.getDate());
            d.setMinTemp(rs.getDay().mintemp_c);
            d.setAvgTemp(rs.getDay().avgtemp_c);
            d.setMaxTemp(rs.getDay().maxtemp_c);
            dayList.add(d);
        }

        response.setDayTemp(dayList);
        return response;


    }


}
