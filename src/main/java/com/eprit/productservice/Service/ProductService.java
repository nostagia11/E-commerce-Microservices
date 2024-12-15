package com.eprit.productservice.Service;

import com.eprit.productservice.Model.Product;
import com.eprit.productservice.Repository.ProductRepository;
import com.eprit.productservice.dto.ProductRequest;
import com.eprit.productservice.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .skuCode(productRequest.skuCode())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product created successfully");
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                product.getSkuCode(),
                product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                        product.getSkuCode(),
                        product.getPrice()))
                .toList();
    }
}
