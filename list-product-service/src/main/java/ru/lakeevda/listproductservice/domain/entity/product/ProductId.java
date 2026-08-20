package ru.lakeevda.listproductservice.domain.entity.product;

import lombok.Getter;

@Getter
public class ProductId {
    private final Long value;

    private ProductId(Long value) {
        this.value = value;
    }

    public static ProductId of(Long value) {
        if (value == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("Product ID must be positive");
        }
        return new ProductId(value);
    }
}
