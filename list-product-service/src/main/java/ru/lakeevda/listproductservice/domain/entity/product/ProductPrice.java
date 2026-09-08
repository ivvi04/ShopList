package ru.lakeevda.listproductservice.domain.entity.product;

import lombok.Getter;

import java.math.BigInteger;

@Getter
public class ProductPrice {
    private final BigInteger value;

    private ProductPrice(BigInteger value) {
        this.value = value;
    }

    public static ProductPrice of(BigInteger value) {
        if (value == null) {
            throw new IllegalArgumentException("Стоимость продукта не может быть null");
        }
        if (value.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Стоимость продукта должна быть положительной");
        }
        return new ProductPrice(value);
    }
}
