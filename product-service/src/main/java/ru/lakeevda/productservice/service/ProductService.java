package ru.lakeevda.productservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.productservice.entity.Product;
import ru.lakeevda.productservice.exception.DataNotFoundException;
import ru.lakeevda.productservice.exception.ProductExistException;
import ru.lakeevda.productservice.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Продукт не найден!"));
    }

    public List<Product> findProductsByListId(Long listId) {
        return productRepository.findProductsByListId(listId);
    }

    @Transactional
    public Product addProduct(Product newProduct) {
        if (productRepository.existsByNameAndListId(newProduct.getName(), newProduct.getListId()))
            throw new ProductExistException("Продукт с таким название уже существует!");
        return productRepository.save(newProduct);
    }

    @Transactional
    public void updateProduct(Product updateProduct) {
        if (findProductById(updateProduct.getId()) != null) productRepository.save(updateProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }

    @Transactional
    public void deleteAllPurchasedProducts(Long listId) {
        productRepository.deleteProductsByListIdAndPurchasedTrue(listId);
    }
}
