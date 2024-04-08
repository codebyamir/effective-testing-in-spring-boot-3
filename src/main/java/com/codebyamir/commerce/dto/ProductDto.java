package com.codebyamir.commerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private long id;
    private String productName;
    private String productDescription;
    private String unitPrice;
    private int unitsInStock;

}
