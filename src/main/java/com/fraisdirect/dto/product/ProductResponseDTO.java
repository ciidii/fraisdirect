package com.fraisdirect.dto.product;

import com.fraisdirect.dto.SubCategoryResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDTO {
    private Long productID;
    private String codeProduct;
    private String description;
    private float basicPrice;
    private int quantity;
    private boolean status;
    private List<ProductImageResponseDTO> urls;
    private SubCategoryResponseDTO subCategoryResponseDTO;
}
