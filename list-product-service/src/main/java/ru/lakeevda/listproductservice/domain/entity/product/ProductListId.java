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
            throw new IllegalArgumentException("Идентификатор списка продукта не может быть null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("Идентификатор списка продукта должен быть положительным");
        }
        return new ProductListId(value);
    }
}
