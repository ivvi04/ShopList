package ru.lakeevda.listproductservice.application.boundary.model.list;

import java.util.List;

public record ListResponse(Long id, String name, List<ListUserResponse> userPhones) {
}
