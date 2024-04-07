package com.steelcityamir.commerce.mapper;

import com.steelcityamir.commerce.dto.ProductDto;
import com.steelcityamir.commerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "unitPrice", expression = "java(product.getUnitPrice().toString())")
    ProductDto entityToDto(Product product);
    Product dtoToEntity(ProductDto productDto);
    List<ProductDto> entityListToDtoList(List<Product> products);
}
