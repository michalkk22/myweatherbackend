package com.example.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.service.ForecastService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ForecastController {
    private final ForecastService weekForecastService;

    public ForecastController(ForecastService weekForecastService) {
        this.weekForecastService = weekForecastService;
    }

    @GetMapping("/")
    public String getWeekForecast(
        @RequestParam double latitude,
        @RequestParam double longitude
    ) {
        return weekForecastService.getWeekForecast(latitude, longitude);
    }

    @GetMapping("/summary")
    public String getWeekSummary(
        @RequestParam double latitude,
        @RequestParam double longitude
    ) {
        return weekForecastService.getWeekSummary(latitude, longitude);
    }
}
