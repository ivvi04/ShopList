package ru.lakeevda.listproductservice.application.port.in;

import ru.lakeevda.listproductservice.application.boundary.model.product.ProductDto;

import java.util.List;

public interface ProductUseCase {
    ProductDto getProductById(Long id);
    List<ProductDto> getProductsByListId(Long listId);
    ProductDto create(ProductDto product);
    void update(ProductDto product);
    void delete(Long id);
    void deleteAllPurchasedProducts(Long listId);
}
