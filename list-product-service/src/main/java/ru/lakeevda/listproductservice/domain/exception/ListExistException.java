package ru.lakeevda.listproductservice.domain.exception;

public class ListExistException extends RuntimeException {
    public ListExistException(String message) {
        super(message);
    }
}
