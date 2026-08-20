package ru.lakeevda.listproductservice.domain.exception;

public class ProductExistException extends RuntimeException {
    public ProductExistException(String message) {
        super(message);
    }
}
