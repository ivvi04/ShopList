package ru.lakeevda.listproductservice.domain.entity.product;

import lombok.Getter;

@Getter
public class ProductListId {
    private final Long value;

    private ProductListId(Long value) {
        this.value = value;
    }

    public static ProductListId of(Long value) {
        if (value == null) {
            throw new IllegalArgumentException("List ID cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("List ID must be positive");
        }
        return new ProductListId(value);
    }
}
