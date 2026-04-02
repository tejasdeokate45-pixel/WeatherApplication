package com.weather.WeatherApp.controller;

import com.weather.WeatherApp.dto.WeatherForeCastResponse;
import com.weather.WeatherApp.dto.WeatherResponse;
import com.weather.WeatherApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class WeatherController {
    @Autowired
    WeatherService service;

    @GetMapping("/{city}")
    public WeatherResponse getWeather(@PathVariable String city){
        return service.getData(city);
    }

    @GetMapping("/forecast")
    public WeatherForeCastResponse getForecast(@RequestParam String city, @RequestParam int days){

        return service.getForecast(city,days);
    }

}
