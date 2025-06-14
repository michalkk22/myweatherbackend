package com.example.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.service.WeekForecastService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/week")
public class WeekForecastController {
    private final WeekForecastService weekForecastService;

    public WeekForecastController(WeekForecastService weekForecastService) {
        this.weekForecastService = weekForecastService;
    }

    @GetMapping("/")
    public String getForecast(
        @RequestParam double latitude,
        @RequestParam double longitude
    ) {
        return weekForecastService.getForecast(latitude, longitude);
    }

    @GetMapping("/summary")
    public String getSummary(
        @RequestParam double latitude,
        @RequestParam double longitude
    ) {
        return weekForecastService.getSummary(latitude, longitude);
    }
}
