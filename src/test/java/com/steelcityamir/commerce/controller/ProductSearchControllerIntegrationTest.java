package com.steelcityamir.commerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.steelcityamir.commerce.dto.SearchCriteriaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductSearchControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * This test verifies the behavior of POST /search given a keyword that matches a product
     *
     * @throws Exception
     */
    @Test
    void searchProducts_happyPath() throws Exception {
        SearchCriteriaDto searchCriteriaDto = new SearchCriteriaDto();
        searchCriteriaDto.setKeyword("macbook");

        mockMvc.perform(post("/search")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // This adds the CSRF token
                        .content(objectMapper.writeValueAsString(searchCriteriaDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].productName").value("Apple MacBook Air 13 in."))
                .andExpect(jsonPath("$.[0].productDescription").value("M3 processor, 8GB RAM, 256GB SSD"))
                .andExpect(jsonPath("$.[0].unitPrice").value("1099.00"))
                .andExpect(jsonPath("$.[0].unitsInStock").value(3500));
    }
}
