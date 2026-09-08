package ru.lakeevda.listproductservice.application.port.in;

import ru.lakeevda.listproductservice.application.boundary.model.product.ProductRequest;
import ru.lakeevda.listproductservice.application.boundary.model.product.ProductResponse;

import java.util.List;

public interface ProductUseCase {
    ProductResponse getById(Long id);
    List<ProductResponse> getAllByListId(Long listId);
    ProductResponse create(ProductRequest productRequest);
    void update(Long id, ProductRequest productRequest);
    void delete(Long id);
    void deleteAllPurchasedProducts(Long listId);
}
