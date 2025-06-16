package com.example.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.dto.DailyForecastResponse;
import com.example.dto.WeekSummaryResponse;
import com.example.service.ForecastService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@RestController
@RequestMapping("/week")
public class ForecastController {
    private final ForecastService weekForecastService;

    public ForecastController(ForecastService weekForecastService) {
        this.weekForecastService = weekForecastService;
    }

    @GetMapping
    public List<DailyForecastResponse> getWeekForecast(
            @RequestParam @Min(-90) @Max(90) double latitude,
            @RequestParam @Min(-180) @Max(180) double longitude
    ) {
        return weekForecastService.getWeekForecast(latitude, longitude);
    }

    @GetMapping("/summary")
    public WeekSummaryResponse getWeekSummary(
            @RequestParam @Min(-90) @Max(90) double latitude,
            @RequestParam @Min(-180) @Max(180) double longitude
    ) {
        return weekForecastService.getWeekSummary(latitude, longitude);
    }
}
