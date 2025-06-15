package com.example.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.dto.DailyForecastResponse;
import com.example.dto.WeekSummaryResponse;
import com.example.service.ForecastService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/week")
public class ForecastController {
    private final ForecastService weekForecastService;

    public ForecastController(ForecastService weekForecastService) {
        this.weekForecastService = weekForecastService;
    }

    @GetMapping
    public List<DailyForecastResponse> getWeekForecast(
        @RequestParam double latitude,
        @RequestParam double longitude
    ) {
        return weekForecastService.getWeekForecast(latitude, longitude);
    }

    @GetMapping("/summary")
    public WeekSummaryResponse getWeekSummary(
        @RequestParam double latitude,
        @RequestParam double longitude
    ) {
        return weekForecastService.getWeekSummary(latitude, longitude);
    }
}
