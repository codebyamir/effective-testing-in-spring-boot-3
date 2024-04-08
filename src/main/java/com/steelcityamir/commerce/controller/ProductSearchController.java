package com.steelcityamir.commerce.controller;

import com.steelcityamir.commerce.dto.ProductDto;
import com.steelcityamir.commerce.dto.SearchCriteriaDto;
import com.steelcityamir.commerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class ProductSearchController {

    private final ProductService productService;

    public ProductSearchController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<List<ProductDto>> searchProducts(@RequestBody SearchCriteriaDto searchCriteriaDto) {
        List<ProductDto> productDtos = productService.searchByKeyword(searchCriteriaDto.getKeyword());
        return ResponseEntity.ok(productDtos);
    }
}
