package ru.lakeevda.listproductservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.listproductservice.entity.ProductEntity;
import ru.lakeevda.listproductservice.exception.DataNotFoundException;
import ru.lakeevda.listproductservice.exception.ProductExistException;
import ru.lakeevda.listproductservice.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductEntity findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Продукт не найден!"));
    }

    public List<ProductEntity> findProductsByListId(Long listId) {
        return productRepository.findProductsByListId(listId);
    }

    @Transactional
    public ProductEntity addProduct(ProductEntity newProduct) {
        if (productRepository.existsByNameAndListId(newProduct.getName(), newProduct.getShopListId()))
            throw new ProductExistException("Продукт с таким название уже существует!");
        return productRepository.save(newProduct);
    }

    @Transactional
    public void updateProduct(ProductEntity updateProduct) {
        if (findProductById(updateProduct.getId()) != null) productRepository.save(updateProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        ProductEntity product = findProductById(id);
        productRepository.delete(product);
    }

    @Transactional
    public void deleteAllPurchasedProducts(Long listId) {
        productRepository.deleteProductsByListIdAndPurchasedTrue(listId);
    }
}
