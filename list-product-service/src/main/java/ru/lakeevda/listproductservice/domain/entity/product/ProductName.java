package ru.lakeevda.listproductservice.domain.entity.product;

import lombok.Getter;

@Getter
public class ProductName {
    private final String value;

    private ProductName(String value) {
        this.value = value;
    }

    public static ProductName of(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Название продукта не может быть null или пустым");
        }
        return new ProductName(value.trim());
    }
}
