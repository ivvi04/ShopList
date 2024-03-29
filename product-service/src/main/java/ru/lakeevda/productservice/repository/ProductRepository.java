package ru.lakeevda.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lakeevda.productservice.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByListId(Long listId);
    boolean existByNameAndListId(String name, Long listId);
    void deleteProductsByListIdAndPurchasedTrue(Long listId);
}
