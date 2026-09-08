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
            throw new IllegalArgumentException("Идентификатор продукта не может быть null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("Идентификатор продукта должен быть положительным");
        }
        return new ProductId(value);
    }
}
