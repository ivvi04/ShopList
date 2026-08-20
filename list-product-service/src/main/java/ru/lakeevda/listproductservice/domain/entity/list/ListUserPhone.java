package ru.lakeevda.listproductservice.domain.entity.list;

import lombok.Getter;

@Getter
public class ListUserPhone {
    private final Long value;

    private ListUserPhone(Long value) {
        this.value = value;
    }

    public static ListUserPhone of(Long value) {
        if (value == null) {
            throw new IllegalArgumentException("User phone cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("User phone must be positive");
        }
        return new ListUserPhone(value);
    }
}
