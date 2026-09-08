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
        return new ProductImage(value.trim());
    }
}
