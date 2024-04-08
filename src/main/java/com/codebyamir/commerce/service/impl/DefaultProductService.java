package com.codebyamir.commerce.service.impl;

import com.codebyamir.commerce.dto.ProductDto;
import com.codebyamir.commerce.entity.Product;
import com.codebyamir.commerce.exception.ProductNotFoundException;
import com.codebyamir.commerce.mapper.ProductMapper;
import com.codebyamir.commerce.repository.ProductRepository;
import com.codebyamir.commerce.service.ProductService;
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
