package com.example.model;

import java.util.List;

public class ForecastApiModel {
    private final Daily daily;
    // Other fields can be added as needed
    
    public ForecastApiModel(Daily daily) {
        this.daily = daily;
    }

    public static class Daily {
        private final List<String> time; // yyyy-mm-dd format
        private final List<Double> temperature_2m_max; // Celsuis by default
        private final List<Double> temperature_2m_min;
        private final List<Integer> weathercode;
        private final List<Double> sunshine_duration; // in seconds
        
        public Daily(List<String> time, List<Double> temperature_2m_max, List<Double> temperature_2m_min,
                List<Integer> weathercode, List<Double> sunshine_duration) {
            this.time = time;
            this.temperature_2m_max = temperature_2m_max;
            this.temperature_2m_min = temperature_2m_min;
            this.weathercode = weathercode;
            this.sunshine_duration = sunshine_duration;
        }

        public List<String> getTime() {
            return time;
        }

        public List<Double> getTemperature_2m_max() {
            return temperature_2m_max;
        }

        public List<Double> getTemperature_2m_min() {
            return temperature_2m_min;
        }

        public List<Integer> getWeathercode() {
            return weathercode;
        }

        public List<Double> getSunshine_duration() {
            return sunshine_duration;
        }
    }
    
    public Daily getDaily() {
        return daily;
    }
}
