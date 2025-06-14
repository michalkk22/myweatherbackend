package com.example.client;

import org.springframework.web.client.RestTemplate;

import com.example.config.WeatherApiProperties;
import com.example.model.ForecastApiModel;

public class ForecastClient {
    private final RestTemplate restTemplate;
    private final WeatherApiProperties properties;

    public ForecastClient(RestTemplate restTemplate, WeatherApiProperties weatherApiProperties) {
        this.restTemplate = restTemplate;
        this.properties = weatherApiProperties;
    }

    public ForecastApiModel getWeekForecast(double latitude, double longitude) {
        String url = String.format("%s?latitude=%.2f&longitude=%.2f&daily=temperature_2m_max,temperature_2m_min,weathercode,sunshine_duration&timezone=auto",
                properties.getUrl(), latitude, longitude);
        
        return restTemplate.getForObject(url, ForecastApiModel.class);
    }
}
