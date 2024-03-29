package ru.lakeevda.gatewayservice.exception;

public class UnAuthAccessToAppException extends RuntimeException {

    public UnAuthAccessToAppException(String message) {
        super(message);
    }
}
