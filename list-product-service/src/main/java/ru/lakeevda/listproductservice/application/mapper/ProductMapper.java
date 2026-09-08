package ru.lakeevda.listproductservice.application.mapper;

import ru.lakeevda.listproductservice.application.boundary.model.product.ProductRequest;
import ru.lakeevda.listproductservice.application.boundary.model.product.ProductResponse;
import ru.lakeevda.listproductservice.domain.entity.product.Product;
import ru.lakeevda.listproductservice.domain.entity.product.ProductImage;
import ru.lakeevda.listproductservice.domain.entity.product.ProductListId;
import ru.lakeevda.listproductservice.domain.entity.product.ProductName;
import ru.lakeevda.listproductservice.domain.entity.product.ProductPrice;
import ru.lakeevda.listproductservice.domain.entity.product.ProductUrl;

public class ProductMapper {

    public static ProductResponse toDto(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductResponse(
                product.getId().getValue(),
                product.getListId().getValue(),
                product.getName().getValue(),
                product.getPrice().getValue(),
                product.getUrl().getValue(),
                product.getImage().getValue(),
                product.getPurchased()
        );
    }

    public static Product toEntity(ProductRequest productRequest) {
        if (productRequest == null) {
            return null;
        }

        return Product.create(ProductListId.of(productRequest.listId()), ProductName.of(productRequest.name()),
                ProductPrice.of(productRequest.price()), ProductUrl.of(productRequest.url()), ProductImage.of(productRequest.image()),
                productRequest.purchased());
    }
}
