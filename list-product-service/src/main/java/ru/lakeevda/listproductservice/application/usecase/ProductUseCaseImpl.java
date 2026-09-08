package ru.lakeevda.listproductservice.application.usecase;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import ru.lakeevda.listproductservice.application.boundary.model.product.ProductRequest;
import ru.lakeevda.listproductservice.application.boundary.model.product.ProductResponse;
import ru.lakeevda.listproductservice.application.mapper.ProductMapper;
import ru.lakeevda.listproductservice.application.port.in.ProductUseCase;
import ru.lakeevda.listproductservice.domain.entity.product.Product;
import ru.lakeevda.listproductservice.domain.entity.product.ProductImage;
import ru.lakeevda.listproductservice.domain.entity.product.ProductListId;
import ru.lakeevda.listproductservice.domain.entity.product.ProductName;
import ru.lakeevda.listproductservice.domain.entity.product.ProductPrice;
import ru.lakeevda.listproductservice.domain.entity.product.ProductUrl;
import ru.lakeevda.listproductservice.domain.exception.ProductNotFoundException;
import ru.lakeevda.listproductservice.domain.exception.ProductExistException;
import ru.lakeevda.listproductservice.domain.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse getById(Long id) {
        Product product = getProduct(id);
        return ProductMapper.toDto(product);
    }

    @Override
    public List<ProductResponse> getAllByListId(Long listId) {
        List<Product> products = productRepository.findAllByListId(listId);
        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse create(ProductRequest productRequest) {
        if (productRepository.existsByNameAndListId(productRequest.name(), productRequest.listId())) {
            throw new ProductExistException();
        }

        Product productEntity = ProductMapper.toEntity(productRequest);
        Product savedProduct = productRepository.save(productEntity);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public void update(Long id, ProductRequest productRequest) {
        Product existingProduct = getProduct(id);

        Product updatedProduct = Product.restore(
                existingProduct.getId(),
                ProductListId.of(productRequest.listId()),
                ProductName.of(productRequest.name()),
                ProductPrice.of(productRequest.price()),
                ProductUrl.of(productRequest.url()),
                ProductImage.of(productRequest.image()),
                productRequest.purchased());

        productRepository.save(updatedProduct);
    }

    @Override
    public void delete(Long id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }

    @Override
    public void deleteAllPurchasedProducts(Long listId) {
        productRepository.deleteByListIdAndPurchasedTrue(listId);
    }

    private @NonNull Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }
}
