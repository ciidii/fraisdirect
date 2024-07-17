package com.fraisdirect.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryResponseDTO {
    @NotNull
    private Long categoryID;
    private String nameCategory;
    private String description;
}
