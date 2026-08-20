package ru.lakeevda.listproductservice.domain.exception;

public class UserNotAuthorException extends RuntimeException {
    public UserNotAuthorException(String message) {
        super(message);
    }
}
