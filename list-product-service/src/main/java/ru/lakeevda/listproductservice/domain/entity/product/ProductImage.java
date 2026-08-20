package ru.lakeevda.listproductservice.domain.entity.product;

import lombok.Getter;

@Getter
public class ProductImage {
    private final String value;

    private ProductImage(String value) {
        this.value = value;
    }

    public static ProductImage of(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        if (value.length() > 500) {
            throw new IllegalArgumentException("Image URL cannot exceed 500 characters");
        }
        return new ProductImage(value.trim());
    }
}
