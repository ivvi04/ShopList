package ru.lakeevda.userservice.exception;

public class OtherUserExistException extends RuntimeException {

    public OtherUserExistException(String message) {
        super(message);
    }
}
