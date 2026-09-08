package ru.lakeevda.listproductservice.domain.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Продукт не найден");
    }
}
