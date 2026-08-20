package ru.lakeevda.listproductservice.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.listproductservice.infrastructure.entity.ProductJpaEntity;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {
    List<ProductJpaEntity> findByListId(Long listId);
    
    boolean existsByNameAndListId(String name, Long listId);
    
    void deleteByListIdAndPurchasedTrue(Long listId);
}
