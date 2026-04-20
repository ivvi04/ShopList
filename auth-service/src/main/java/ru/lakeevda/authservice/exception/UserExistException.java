package ru.lakeevda.authservice.exception;

public class UserExistException extends RuntimeException {

    public UserExistException(String message) {
        super(message);
    }
}
