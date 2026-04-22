package ru.lakeevda.listproductservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.listproductservice.entity.ProductEntity;
import ru.lakeevda.listproductservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable Long id) {
        ProductEntity product = productService.findProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/{listId}/products")
    public ResponseEntity<List<ProductEntity>> getProducts(@PathVariable Long listId) {
        List<ProductEntity> productList = productService.findProductsByListId(listId);
        return ResponseEntity.ok().body(productList);
    }

    @PostMapping
    public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductEntity product) {
        return ResponseEntity.ok().body(productService.addProduct(product));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateProduct(@RequestBody ProductEntity product) {
        productService.updateProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{listId}/delPurchased")
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
