package com.steelcityamir.commerce.service;

import com.steelcityamir.commerce.dto.ProductDto;
import com.steelcityamir.commerce.entity.Product;
import com.steelcityamir.commerce.mapper.ProductMapper;
import com.steelcityamir.commerce.repository.ProductRepository;
import com.steelcityamir.commerce.service.impl.DefaultProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
