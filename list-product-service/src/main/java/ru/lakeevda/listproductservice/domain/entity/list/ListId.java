package ru.lakeevda.listproductservice.domain.entity.list;

import lombok.Getter;

@Getter
public class ListId {
    private final Long value;

    private ListId(Long value) {
        this.value = value;
    }

    public static ListId of(Long value) {
        if (value == null) {
            throw new IllegalArgumentException("Идентификатор списка не может быть null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("Идентификатор списка должен быть положительным");
        }
        return new ListId(value);
    }
}
