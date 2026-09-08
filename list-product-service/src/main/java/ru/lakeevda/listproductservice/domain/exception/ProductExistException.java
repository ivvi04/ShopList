package ru.lakeevda.listproductservice.domain.exception;

public class ProductExistException extends RuntimeException {
    public ProductExistException() {
        super("Продукт с таким названием уже существует");
    }
}
