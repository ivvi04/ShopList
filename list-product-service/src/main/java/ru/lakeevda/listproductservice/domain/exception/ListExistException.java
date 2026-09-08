package ru.lakeevda.listproductservice.domain.exception;

public class ListExistException extends RuntimeException {
    public ListExistException() {
        super("Список с таким названием уже существует");
    }
}
