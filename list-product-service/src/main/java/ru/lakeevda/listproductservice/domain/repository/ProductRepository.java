package ru.lakeevda.listproductservice.domain.repository;

import ru.lakeevda.listproductservice.domain.entity.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long id);
    List<Product> findAllByListId(Long listId);
    boolean existsByNameAndListId(String name, Long listId);
    Product save(Product product);
    void delete(Product product);
    void deleteByListIdAndPurchasedTrue(Long listId);
}
