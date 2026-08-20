package ru.lakeevda.listproductservice.domain.exception;

public class ListNotFoundException extends RuntimeException {
    public ListNotFoundException(String message) {
        super(message);
    }
}
