package ru.lakeevda.listproductservice.enums;

import java.util.Arrays;

public enum ShopListStatus {
    CREATED("CREATED"),
    UPDATED("UPDATED"),
    DELETED("DELETED");

    private final String value;

    ShopListStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public ShopListStatus fromValue(String text) {
        return Arrays.stream(ShopListStatus.values())
                .filter(candidate -> candidate.value.equals(text))
                .findFirst()
                .orElse(null);
    }
}
