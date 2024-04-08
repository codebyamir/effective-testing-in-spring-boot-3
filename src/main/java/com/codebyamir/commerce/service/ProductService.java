package com.codebyamir.commerce.service;

import com.codebyamir.commerce.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto getProduct(long id);
    List<ProductDto> getAllProducts();
    List<ProductDto> searchByKeyword(String keyword);
}
