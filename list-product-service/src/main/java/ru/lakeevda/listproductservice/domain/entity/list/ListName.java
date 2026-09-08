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
            throw new IllegalArgumentException("Название списка не может быть null или пустым");
        }
        return new ListName(value.trim());
    }
}
