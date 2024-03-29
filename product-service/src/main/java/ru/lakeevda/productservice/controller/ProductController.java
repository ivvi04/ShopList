package ru.lakeevda.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.productservice.entity.Product;
import ru.lakeevda.productservice.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/products/{listId}")
    public ResponseEntity<List<Product>> getLists(@PathVariable Long listId) {
        List<Product> productList = productService.findProductsByListId(listId);
        return ResponseEntity.ok().body(productList);
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{listId}")
    public ResponseEntity<Void> deleteAllPurchasedProducts(@PathVariable Long listId) {
        productService.deleteAllPurchasedProducts(listId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
