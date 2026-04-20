package ru.lakeevda.listproductservice.exception;

public class ProductExistException extends RuntimeException {

    public ProductExistException(String message) {
        super(message);
    }
}
