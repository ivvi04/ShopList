package ru.lakeevda.listproductservice.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.listproductservice.domain.entity.product.Product;
import ru.lakeevda.listproductservice.domain.repository.ProductRepository;
import ru.lakeevda.listproductservice.infrastructure.entity.ProductJpaEntity;
import ru.lakeevda.listproductservice.infrastructure.mapper.ProductMapper;
import ru.lakeevda.listproductservice.infrastructure.repository.ProductJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public Optional<Product> findById(Long id) {
        ProductJpaEntity entity = productJpaRepository.findById(id).orElse(null);
        return entity != null ? Optional.of(ProductMapper.toDomain(entity)) : Optional.empty();
    }

    @Override
    public List<Product> findAllByListId(Long listId) {
        List<ProductJpaEntity> entities = productJpaRepository.findByListId(listId);
        return entities.stream()
                .map(ProductMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByNameAndListId(String name, Long listId) {
        return productJpaRepository.existsByNameAndListId(name, listId);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        ProductJpaEntity entity = ProductMapper.toEntity(product);
        ProductJpaEntity saved = productJpaRepository.save(entity);
        return ProductMapper.toDomain(saved);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        productJpaRepository.deleteById(product.getId().getValue());
    }

    @Override
    @Transactional
    public void deleteByListIdAndPurchasedTrue(Long listId) {
        productJpaRepository.deleteByListIdAndPurchasedTrue(listId);
    }
}
