package ru.lakeevda.authservice.exception;

public class UserPhoneExistException extends RuntimeException {

    public UserPhoneExistException(String message) {
        super(message);
    }
}
