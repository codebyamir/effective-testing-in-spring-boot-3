package com.codebyamir.commerce.service;

import com.codebyamir.commerce.dto.ProductDto;
import com.codebyamir.commerce.entity.Product;
import com.codebyamir.commerce.mapper.ProductMapper;
import com.codebyamir.commerce.repository.ProductRepository;
import com.codebyamir.commerce.service.impl.DefaultProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultProductServiceTest {

    @InjectMocks
    DefaultProductService service;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductMapper productMapper;

    private static final long PRODUCT_ID = 1L;

    @Test
    void getProduct_idNotFound_throwsException() {
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.empty());

        try {
            service.getProduct(PRODUCT_ID);
            fail("Expected exception to be thrown.");
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Product not found with id: 1");
        }
    }

    @Test
    void getProduct_idFound() {
        Product product = new Product();
        product.setId(PRODUCT_ID);

        ProductDto productDto = new ProductDto();
        productDto.setId(PRODUCT_ID);

        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
        when(productMapper.entityToDto(any(Product.class))).thenReturn(productDto);

        ProductDto result = service.getProduct(PRODUCT_ID);

        assertThat(result)
                .isNotNull()
                .isEqualTo(productDto);

        verify(productRepository).findById(PRODUCT_ID);
        verify(productMapper).entityToDto(product);
    }

    @Test
    void searchByKeyword_foundResults() {
        String searchKeyword = "apple";

        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setProductName("Apple MacBook Pro");
        product.setUnitPrice(BigDecimal.valueOf(999.99));

        ProductDto productDto = new ProductDto();
        productDto.setId(PRODUCT_ID);
        productDto.setProductName("Apple MacBook Pro");
        productDto.setUnitPrice("999.99");

        when(productRepository.findByProductNameContainingIgnoreCase(searchKeyword)).thenReturn(List.of(product));
        when(productMapper.entityListToDtoList(any(List.class))).thenReturn(List.of(productDto));

        List<ProductDto> results = service.searchByKeyword(searchKeyword);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getId()).isEqualTo(product.getId());
        assertThat(results.get(0).getProductName()).isEqualTo(product.getProductName());
        assertThat(results.get(0).getUnitPrice()).isEqualTo(product.getUnitPrice().toString());
    }
}
