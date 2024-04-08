package com.steelcityamir.commerce.service.impl;

import com.steelcityamir.commerce.dto.ProductDto;
import com.steelcityamir.commerce.entity.Product;
import com.steelcityamir.commerce.exception.ProductNotFoundException;
import com.steelcityamir.commerce.mapper.ProductMapper;
import com.steelcityamir.commerce.repository.ProductRepository;
import com.steelcityamir.commerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public DefaultProductService(ProductRepository productRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDto getProduct(long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new ProductNotFoundException(id);
        }

        return mapper.entityToDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return mapper.entityListToDtoList(products);
    }

    @Override
    public List<ProductDto> searchByKeyword(String keyword) {
        List<Product> products = productRepository.findByProductNameContainingIgnoreCase(keyword);
        return mapper.entityListToDtoList(products);
    }
}
