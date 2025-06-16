package com.example.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.config.WeatherApiProperties;
import com.example.model.ForecastApiModel;

import reactor.core.publisher.Mono;

@Component
public class ForecastClient {
    private final WebClient webClient;
    // Uncomment if there are more properties than URL
    // private final WeatherApiProperties properties;

    public ForecastClient(WebClient.Builder builder, WeatherApiProperties properties) {
        this.webClient = builder.baseUrl(properties.getUrl()).build();
    }

    public ForecastApiModel getWeekForecast(double latitude, double longitude) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("daily", "temperature_2m_max", "temperature_2m_min", "weathercode",
                                "sunshine_duration", "surface_pressure_mean")
                        .queryParam("timezone", "auto")
                        .build())
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            return Mono.error(new RuntimeException("API error: " + errorBody));
                        }))
                .bodyToMono(ForecastApiModel.class)
                .block();
    }
}
