package com.steelcityamir.commerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * This test verifies the behavior of GET /products
     *
     * @throws Exception
     */
    @Test
    void getAllProducts_happyPath() throws Exception {
        mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[2].id").value(3));
    }

    /**
     * This test verifies the behavior of GET /products/{id} given an id that exists
     *
     * @throws Exception
     */
    @Test
    void getProductById_whenProductExists_returnsProduct() throws Exception {
        mockMvc.perform(get("/products/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("Product 3"))
                .andExpect(jsonPath("$.description").value("Description of Product 3"))
                .andExpect(jsonPath("$.unitPrice").value("39.99"))
                .andExpect(jsonPath("$.unitsInStock").value(200));
    }

    /**
     * This test verifies the behavior of GET /products/{id} given an id that doesn't exist
     *
     * @throws Exception
     */
    @Test
    void getProductById_whenProductDoesNotExist_returns404NotFound() throws Exception {
        mockMvc.perform(get("/products/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Product not found with id: 100"));
    }
}
