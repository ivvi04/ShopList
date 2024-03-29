package ru.lakeevda.gatewayservice.exception;

public class MissingAuthHeaderException extends RuntimeException {

    public MissingAuthHeaderException(String message) {
        super(message);
    }
}
