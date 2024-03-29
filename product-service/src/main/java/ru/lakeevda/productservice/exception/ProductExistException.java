package ru.lakeevda.productservice.exception;

public class ProductExistException extends RuntimeException {

    public ProductExistException(String message) {
        super(message);
    }
}
