package com.fraisdirect.dto.product;

import com.fraisdirect.dto.SubCategoryResponseDTO;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDTO {
    private Long productID;
    private String name;
    private String codeProduct;
    private String description;
    private float basicPrice;
    private int quantity;
    private boolean status;
    @ElementCollection
    private List<byte[]> images;
    private SubCategoryResponseDTO subCategoryResponseDTO;
}
