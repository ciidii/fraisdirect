package com.fraisdirect.dto;

import com.fraisdirect.entity.Attribute;
import com.fraisdirect.entity.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class SubCategoryResponseDTO{
    private Long subCategoryID;
    private String nameSubCategory;
    private String descriptionSubCategory;
    private CategoryResponseDTO category;
    private List<AttributeResponseDTO> attributes;
}
