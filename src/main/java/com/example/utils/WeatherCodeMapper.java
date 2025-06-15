package com.example.utils;

import java.util.Comparator;
import java.util.Map;

public class WeatherCodeMapper {

    private static final Map<Integer, String> siplmified = Map.ofEntries(
        Map.entry(0, "Clear sky"),
        Map.entry(3, "Cloudy"),
        Map.entry(45, "Fog"),
        Map.entry(55, "Drizzle"),
        Map.entry(65, "Rain"),
        Map.entry(74, "Snow"),
        Map.entry(81, "Rain showers"),
        Map.entry(86, "Snow showers"),
        Map.entry(95, "Thunderstorm")
    );

    public static String getNearestSimplified(int code) {
    return siplmified.entrySet().stream()
        .min(Comparator.comparingInt(entry -> Math.abs(entry.getKey() - code)))
        .map(Map.Entry::getValue)
        .orElse("Unknown");
    }

}
