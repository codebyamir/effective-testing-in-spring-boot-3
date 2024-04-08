package com.steelcityamir.commerce.service;

import com.steelcityamir.commerce.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto getProduct(long id);
    List<ProductDto> getAllProducts();
    List<ProductDto> searchByKeyword(String keyword);
}
