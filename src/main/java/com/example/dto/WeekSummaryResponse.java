package com.example.dto;

public class WeekSummaryResponse {
    final double pressure;
    final double sunshine;
    final double temperatureMax;
    final double temperatureMin;
    final String[] weather;
    
    public WeekSummaryResponse(double pressure, double sunshine, double temperatureMax, double temperatureMin,
            String[] weather) {
        this.pressure = pressure;
        this.sunshine = sunshine;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.weather = weather;
    }

    public double getPressure() {
        return pressure;
    }

    public double getSunshine() {
        return sunshine;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public String[] getWeather() {
        return weather;
    }
}
