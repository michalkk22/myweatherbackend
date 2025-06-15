package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.client.ForecastClient;
import com.example.dto.DailyForecastResponse;
import com.example.dto.WeekSummaryResponse;
import com.example.model.ForecastApiModel;
import com.example.utils.WeatherCodeMapper;

@Service
public class ForecastService {
    private final ForecastClient forecastClient;

    public ForecastService(ForecastClient forecastClient) {
        this.forecastClient = forecastClient;
    }

    public List<DailyForecastResponse> getWeekForecast(double latitude, double longitude) {
        ForecastApiModel.Daily apiModel = forecastClient.getWeekForecast(latitude, longitude).getDaily();

        List<DailyForecastResponse> response = new ArrayList<>();

        for (int i = 0; i < apiModel.getTime().size(); i++) {
            String date = apiModel.getTime().get(i);
            double temperatureMax = apiModel.getTemperature_2m_max().get(i);
            double temperatureMin = apiModel.getTemperature_2m_min().get(i);
            int weatherCode = apiModel.getWeathercode().get(i);
            double sunshineHours = apiModel.getSunshine_duration().get(i) / 3600.0;

            double installationPower = 2.5;
            double panelEfficiency = 0.2;
            double energy = sunshineHours * installationPower * panelEfficiency;

            DailyForecastResponse dailyForecastResponse = new DailyForecastResponse(date, temperatureMax,
                    temperatureMin, weatherCode, energy);
            response.add(dailyForecastResponse);
        }

        return response;
    }

    public WeekSummaryResponse getWeekSummary(double latitude, double longitude) {
        ForecastApiModel.Daily apiModel = forecastClient.getWeekForecast(latitude, longitude).getDaily();

        double pressure = 0.0;
        double sunshine = 0.0;
        double temperatureMax = Double.MIN_VALUE;
        double temperatureMin = Double.MAX_VALUE;

        int days = apiModel.getTime().size(); // Should be 7, but...
        int[] weatherCodes = new int[days];

        for (int i = 0; i < days; i++) {
            pressure += apiModel.getSurface_pressure_mean().get(i);
            sunshine += apiModel.getSunshine_duration().get(i) / 3600.0; // Convert seconds to hours
            temperatureMax = Math.max(temperatureMax, apiModel.getTemperature_2m_max().get(i));
            temperatureMin = Math.min(temperatureMin, apiModel.getTemperature_2m_min().get(i));
            weatherCodes[i] = apiModel.getWeathercode().get(i);
        }
        pressure /= days;
        sunshine /= days;

        String[] weather = getWeatherDescription(weatherCodes);

        return new WeekSummaryResponse(pressure, sunshine, temperatureMax, temperatureMin,
                weather);
    }

    private String[] getWeatherDescription(int[] weatherCodes) {
        Map<String, Integer> weatherCodeMap = new HashMap<>();
        for (int code : weatherCodes) {
            String description = WeatherCodeMapper.getNearestSimplified(code);
            Integer val = weatherCodeMap.putIfAbsent(description, 0);
            if (val != null) {
                weatherCodeMap.put(description, val + 1);
            }
        }

        String mostFrequent = null;
        String secondMostFrequent = null;
        int maxCount = 0;
        int secondMaxCount = 0;

        for (Map.Entry<String, Integer> entry : weatherCodeMap.entrySet()) {
            int count = entry.getValue();
            if (count > maxCount) {
                secondMostFrequent = mostFrequent;
                secondMaxCount = maxCount;
                mostFrequent = entry.getKey();
                maxCount = count;
            } else if (count > secondMaxCount) {
                secondMostFrequent = entry.getKey();
                secondMaxCount = count;
            }
        }

        if (mostFrequent == null)
            mostFrequent = "";
        if (secondMostFrequent == null)
            secondMostFrequent = "";

        return new String[] { mostFrequent, secondMostFrequent };
    }

}
