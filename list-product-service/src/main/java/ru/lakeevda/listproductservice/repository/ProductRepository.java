package ru.lakeevda.listproductservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lakeevda.listproductservice.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByListId(Long listId);
    boolean existsByNameAndListId(String name, Long listId);
    void deleteProductsByListIdAndPurchasedTrue(Long listId);
}
