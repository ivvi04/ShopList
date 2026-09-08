package ru.lakeevda.listproductservice.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lakeevda.listproductservice.application.boundary.model.product.ProductDto;
import ru.lakeevda.listproductservice.application.mapper.ProductMapper;
import ru.lakeevda.listproductservice.application.port.in.ProductUseCase;
import ru.lakeevda.listproductservice.domain.entity.product.Product;
import ru.lakeevda.listproductservice.domain.entity.product.ProductImage;
import ru.lakeevda.listproductservice.domain.entity.product.ProductListId;
import ru.lakeevda.listproductservice.domain.entity.product.ProductName;
import ru.lakeevda.listproductservice.domain.entity.product.ProductPrice;
import ru.lakeevda.listproductservice.domain.entity.product.ProductUrl;
import ru.lakeevda.listproductservice.domain.exception.DataNotFoundException;
import ru.lakeevda.listproductservice.domain.exception.ProductExistException;
import ru.lakeevda.listproductservice.domain.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {
    private final ProductRepository productRepository;

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Продукт не найден!"));
        return ProductMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getProductsByListId(Long listId) {
        List<Product> products = productRepository.findAllByListId(listId);
        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto create(ProductDto product) {
        if (productRepository.existsByNameAndListId(product.name(), product.listId())) {
            throw new ProductExistException("Продукт с таким названием уже существует!");
        }

        Product productEntity = ProductMapper.toEntity(product);
        Product savedProduct = productRepository.save(productEntity);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public void update(ProductDto product) {
        Product existingProduct = productRepository.findById(product.id())
                .orElseThrow(() -> new DataNotFoundException("Продукт не найден!"));

        Product updatedProduct = Product.restore(
                existingProduct.getId(),
                ProductListId.of(product.listId()),
                ProductName.of(product.name()),
                ProductPrice.of(product.price()),
                ProductUrl.of(product.url()),
                ProductImage.of(product.image()),
                product.purchased());

        productRepository.save(updatedProduct);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Продукт не найден!"));
        productRepository.delete(product);
    }

    @Override
    public void deleteAllPurchasedProducts(Long listId) {
        productRepository.deleteByListIdAndPurchasedTrue(listId);
    }
}
