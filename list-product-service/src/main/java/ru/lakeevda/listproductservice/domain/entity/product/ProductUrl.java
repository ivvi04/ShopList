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
            throw new IllegalArgumentException("Ссылка продукта не может быть null или пустым");
        }
        try {
            new URI(value);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Ссылка продукта имеет некорректный формат", e);
        }
        return new ProductUrl(value.trim());
    }
}
