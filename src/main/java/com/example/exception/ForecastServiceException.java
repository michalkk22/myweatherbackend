package com.example.exception;

public class ForecastServiceException extends RuntimeException {
    public ForecastServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}