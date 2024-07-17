package com.fraisdirect.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SubCategoryResponseDTO{

    @NotNull
    private Long subCategoryID;
    private String nameSubCategory;
    private String descriptionSubCategory;
    @NotNull
    private CategoryResponseDTO category;
    @NotNull
    private List<AttributeResponseDTO> attributes;
}
