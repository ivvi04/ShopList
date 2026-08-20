package ru.lakeevda.listproductservice.domain.exception;

public class UserAuthorIsEmptyException extends RuntimeException {
    public UserAuthorIsEmptyException(String message) {
        super(message);
    }
}
