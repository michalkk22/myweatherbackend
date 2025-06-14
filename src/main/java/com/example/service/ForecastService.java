package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.client.ForecastClient;
import com.example.dto.DailyForecastResponse;
import com.example.model.ForecastApiModel;

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

    public String getWeekSummary(double latitude, double longitude) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSummary'");
    }

}
