package ru.lakeevda.listproductservice.domain.entity.product;

import lombok.Getter;

@Getter
public class Product {
    private ProductId id;
    private final ProductListId listId;
    private final ProductName name;
    private final ProductPrice price;
    private final ProductUrl url;
    private final ProductImage image;
    private final Boolean purchased;

    private Product(ProductListId listId, ProductName name, ProductPrice price, ProductUrl url, ProductImage image, Boolean purchased) {
        this.listId = listId;
        this.name = name;
        this.price = price;
        this.url = url;
        this.image = image;
        this.purchased = purchased;
    }

    private Product(ProductId id, ProductListId listId, ProductName name, ProductPrice price, ProductUrl url, ProductImage image, Boolean purchased) {
        this.listId = listId;
        this.id = id;
        this.name = name;
        this.price = price;
        this.url = url;
        this.image = image;
        this.purchased = purchased;
    }

    public static Product create(ProductListId listId, ProductName name, ProductPrice price, ProductUrl url, ProductImage image, Boolean purchased) {
        return new Product(listId, name, price, url, image, purchased);
    }

    public static Product restore(ProductId id, ProductListId listId, ProductName name, ProductPrice price, ProductUrl url, ProductImage image, Boolean purchased) {
        return new Product(id, listId, name, price, url, image, purchased);
    }
}
