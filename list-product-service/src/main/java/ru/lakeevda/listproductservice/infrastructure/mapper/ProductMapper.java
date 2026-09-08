package ru.lakeevda.listproductservice.infrastructure.mapper;

import ru.lakeevda.listproductservice.domain.entity.product.Product;
import ru.lakeevda.listproductservice.domain.entity.product.ProductId;
import ru.lakeevda.listproductservice.domain.entity.product.ProductImage;
import ru.lakeevda.listproductservice.domain.entity.product.ProductListId;
import ru.lakeevda.listproductservice.domain.entity.product.ProductName;
import ru.lakeevda.listproductservice.domain.entity.product.ProductPrice;
import ru.lakeevda.listproductservice.domain.entity.product.ProductUrl;
import ru.lakeevda.listproductservice.infrastructure.entity.ProductJpaEntity;

public class ProductMapper {

    public static Product toDomain(ProductJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        return Product.restore(
                ProductId.of(entity.getId()),
                ProductListId.of(entity.getListId()),
                ProductName.of(entity.getName()),
                ProductPrice.of(entity.getPrice()),
                ProductUrl.of(entity.getUrl()),
                ProductImage.of(entity.getImage()),
                entity.getPurchased());
    }

    public static ProductJpaEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }

        ProductJpaEntity entity = new ProductJpaEntity();
        entity.setId(product.getId().getValue());
        entity.setName(product.getName().getValue());
        entity.setPrice(product.getPrice().getValue());
        entity.setUrl(product.getUrl().getValue());
        entity.setListId(product.getListId().getValue());

        if (product.getImage() != null) {
            entity.setImage(product.getImage().getValue());
        }

        entity.setPurchased(product.getPurchased());
        return entity;
    }
}
