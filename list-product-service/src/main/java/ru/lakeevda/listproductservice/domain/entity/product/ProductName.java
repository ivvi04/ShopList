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
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (value.length() > 255) {
            throw new IllegalArgumentException("Product name cannot exceed 255 characters");
        }
        return new ProductName(value.trim());
    }
}
