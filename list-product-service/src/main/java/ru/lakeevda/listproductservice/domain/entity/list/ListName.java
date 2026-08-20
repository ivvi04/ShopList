package ru.lakeevda.listproductservice.domain.entity.list;

import lombok.Getter;

@Getter
public class ListName {
    private final String value;

    private ListName(String value) {
        this.value = value;
    }

    public static ListName of(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("List name cannot be null or empty");
        }
        if (value.length() > 255) {
            throw new IllegalArgumentException("List name cannot exceed 255 characters");
        }
        return new ListName(value.trim());
    }
}
