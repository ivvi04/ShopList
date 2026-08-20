package ru.lakeevda.listproductservice.application.mapper;

import ru.lakeevda.listproductservice.application.boundary.model.product.ProductDto;
import ru.lakeevda.listproductservice.domain.entity.product.Product;
import ru.lakeevda.listproductservice.domain.entity.product.ProductId;
import ru.lakeevda.listproductservice.domain.entity.product.ProductImage;
import ru.lakeevda.listproductservice.domain.entity.product.ProductListId;
import ru.lakeevda.listproductservice.domain.entity.product.ProductName;
import ru.lakeevda.listproductservice.domain.entity.product.ProductPrice;
import ru.lakeevda.listproductservice.domain.entity.product.ProductUrl;

public class ProductMapper {

    public static ProductDto toDto(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDto(
                product.getId().getValue(),
                product.getListId().getValue(),
                product.getName().getValue(),
                product.getPrice().getValue(),
                product.getUrl().getValue(),
                product.getImage().getValue(),
                product.getPurchased()
        );
    }

    public static Product toEntity(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        return productDto.id() == null
                ? Product.create(ProductListId.of(productDto.listId()), ProductName.of(productDto.name()),
                ProductPrice.of(productDto.price()), ProductUrl.of(productDto.url()), ProductImage.of(productDto.image()),
                productDto.purchased())
                : Product.restore(ProductId.of(productDto.id()), ProductListId.of(productDto.listId()),
                ProductName.of(productDto.name()), ProductPrice.of(productDto.price()), ProductUrl.of(productDto.url()),
                ProductImage.of(productDto.image()), productDto.purchased());
    }
}
