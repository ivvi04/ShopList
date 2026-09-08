package ru.lakeevda.listproductservice.application.boundary.model.product;

import java.math.BigInteger;

public record ProductResponse(Long id, Long listId, String name, BigInteger price, String url, String image,
                              Boolean purchased) {
}
