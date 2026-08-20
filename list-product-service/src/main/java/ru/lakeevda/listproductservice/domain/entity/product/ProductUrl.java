package ru.lakeevda.listproductservice.domain.entity.product;

import lombok.Getter;

import java.net.URI;
import java.net.URISyntaxException;

@Getter
public class ProductUrl {
    private final String value;

    private ProductUrl(String value) {
        this.value = value;
    }

    public static ProductUrl of(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Product URL cannot be null or empty");
        }
        try {
            new URI(value);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid product URL format", e);
        }
        return new ProductUrl(value.trim());
    }
}
