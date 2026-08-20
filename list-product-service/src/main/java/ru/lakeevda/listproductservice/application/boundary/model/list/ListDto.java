package ru.lakeevda.listproductservice.application.boundary.model.list;

import java.util.List;

public record ListDto(Long id, String name, List<ListUserDto> userPhones) {
}
