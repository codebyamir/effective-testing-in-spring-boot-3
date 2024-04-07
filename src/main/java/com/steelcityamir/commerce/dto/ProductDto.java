package com.steelcityamir.commerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private long id;
    private String name;
    private String description;
    private String unitPrice;
    private int unitsInStock;

}
