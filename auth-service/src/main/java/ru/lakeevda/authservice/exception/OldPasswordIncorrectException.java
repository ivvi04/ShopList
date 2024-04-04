package ru.lakeevda.authservice.exception;

public class OldPasswordIncorrectException extends RuntimeException {

    public OldPasswordIncorrectException(String message) {
        super(message);
    }
}
