package ru.lakeevda.productservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.productservice.entity.Product;
import ru.lakeevda.productservice.exception.DataNotFoundException;
import ru.lakeevda.productservice.exception.ProductExistException;
import ru.lakeevda.productservice.repository.ProductRepository;

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

//    public User findUserById(Long id) {
//        return userRepository.findById(id).orElseThrow(() ->
//                new DataNotFoundException("Пользователь не найден!"));
//    }
//
//    public User findUserByUsername(String username) {
//        return userRepository.findByUsername(username).orElseThrow(() ->
//                new DataNotFoundException("Пользователь не найден!"));
//    }
//
//    public User findUserByUserPhone(Integer userPhone) {
//        return userRepository.findByUserPhone(userPhone).orElseThrow(() ->
//                new DataNotFoundException("Пользователь не найден!"));
//    }

    @Transactional
    public void addProduct(Product product) {
        if (productRepository.existByNameAndListId(product.getName(), product.getListId()))
            throw new ProductExistException("Продукт с таким название уже существует!");
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Product product) {
        Product productFind = findProductById(product.getId());
        productFind = product;
        productRepository.save(productFind);
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
