package com.codebyamir.commerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SearchCriteriaDto {
    @NotBlank(message = "Search keyword is required.")
    private String keyword;
}
