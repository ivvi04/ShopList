package ru.lakeevda.listproductservice.domain.entity.list;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ListStatus {
    CREATED("CREATED"),
    UPDATED("UPDATED"),
    DELETED("DELETED");

    private final String value;

    private ListStatus(String value) {
        this.value = value;
    }

    public static ListStatus fromValue(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("List status cannot be null or empty");
        }

        return Arrays.stream(ListStatus.values())
                .filter(candidate -> candidate.value.equals(text))
                .findFirst()
                .orElse(null);
    }
}
