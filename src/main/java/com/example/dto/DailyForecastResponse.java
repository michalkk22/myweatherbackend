package com.example.dto;

public class DailyForecastResponse {
    private final String date;
    private final double temperatureMax;
    private final double temperatureMin;
    private final int weatherCode;
    private final double energy;

    public DailyForecastResponse(String date, double temperatureMax, double temperatureMin, int weatherCode, double energy) {
        this.date = date;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.weatherCode = weatherCode;
        this.energy = energy;
    }

    public String getDate() {
        return date;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public int getWeatherCode() {
        return weatherCode;
    }
    
    public double getEnergy() {
        return energy;
    }
}
