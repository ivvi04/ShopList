package ru.lakeevda.authservice.exception;

public class ConfirmPasswordIncorrectException extends RuntimeException {

    public ConfirmPasswordIncorrectException(String message) {
        super(message);
    }
}
