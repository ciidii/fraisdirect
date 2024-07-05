package com.fraisdirect.dto;

import com.fraisdirect.entity.SubCategory;
import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long productID;
    private String codeProduct;
    private String description;
    private float basicPrice;
    private boolean status;
    private SubCategoryResponseDTO subCategory;
}
