package ru.lakeevda.listproductservice.exception;

public class UserNotAuthorException extends RuntimeException {

    public UserNotAuthorException(String message) {
        super(message);
    }
}
