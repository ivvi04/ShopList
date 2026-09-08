package ru.lakeevda.listproductservice.adapters.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.listproductservice.application.boundary.model.product.ProductDto;
import ru.lakeevda.listproductservice.application.port.in.ProductUseCase;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductUseCase productUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        ProductDto product = productUseCase.getById(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        return ResponseEntity.ok().body(productUseCase.create(product));
    }

    @PutMapping
    public ResponseEntity<Void> updateProduct(@RequestBody ProductDto product) {
        productUseCase.update(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productUseCase.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list/{listId}")
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable Long listId) {
        List<ProductDto> productList = productUseCase.getProductsByListId(listId);
        return ResponseEntity.ok().body(productList);
    }

    @PutMapping("/list/{listId}")
    public ResponseEntity<Void> deleteAllPurchasedProducts(@PathVariable Long listId) {
        productUseCase.deleteAllPurchasedProducts(listId);
        return ResponseEntity.ok().build();
    }
}
