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
            throw new IllegalArgumentException("List ID cannot be null");
        }
        return new ListId(value);
    }
}
