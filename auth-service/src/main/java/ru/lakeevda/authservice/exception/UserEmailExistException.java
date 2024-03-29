package ru.lakeevda.authservice.exception;

public class UserEmailExistException extends RuntimeException {

    public UserEmailExistException(String message) {
        super(message);
    }
}
