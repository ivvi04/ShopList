package ru.lakeevda.listproductservice.adapters.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.listproductservice.application.boundary.model.product.ProductRequest;
import ru.lakeevda.listproductservice.application.boundary.model.product.ProductResponse;
import ru.lakeevda.listproductservice.application.port.in.ProductUseCase;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductUseCase productUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        ProductResponse product = productUseCase.getById(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest product) {
        return ResponseEntity.ok().body(productUseCase.create(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody ProductRequest product) {
        productUseCase.update(id, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productUseCase.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list/{listId}")
    public ResponseEntity<List<ProductResponse>> getAllByListId(@PathVariable Long listId) {
        List<ProductResponse> productList = productUseCase.getAllByListId(listId);
        return ResponseEntity.ok().body(productList);
    }

    @PutMapping("/list/{listId}")
    public ResponseEntity<Void> deleteAllPurchasedProducts(@PathVariable Long listId) {
        productUseCase.deleteAllPurchasedProducts(listId);
        return ResponseEntity.ok().build();
    }
}
