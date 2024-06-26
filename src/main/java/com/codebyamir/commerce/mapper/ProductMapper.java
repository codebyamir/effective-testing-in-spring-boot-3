package com.codebyamir.commerce.mapper;

import com.codebyamir.commerce.dto.ProductDto;
import com.codebyamir.commerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    @Mapping(target = "unitPrice", expression = "java(product.getUnitPrice().toString())")
    ProductDto entityToDto(Product product);

    List<ProductDto> entityListToDtoList(List<Product> products);
}
