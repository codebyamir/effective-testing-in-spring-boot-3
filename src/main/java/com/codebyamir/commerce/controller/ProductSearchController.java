package com.codebyamir.commerce.controller;

import com.codebyamir.commerce.dto.ProductDto;
import com.codebyamir.commerce.dto.SearchCriteriaDto;
import com.codebyamir.commerce.service.ProductService;
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
