package ru.lakeevda.listproductservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.listproductservice.entity.Product;
import ru.lakeevda.listproductservice.exception.DataNotFoundException;
import ru.lakeevda.listproductservice.exception.ProductExistException;
import ru.lakeevda.listproductservice.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
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
