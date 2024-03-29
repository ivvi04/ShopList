package ru.lakeevda.listservice.exception;

public class UserNotAuthorException extends RuntimeException {

    public UserNotAuthorException(String message) {
        super(message);
    }
}
