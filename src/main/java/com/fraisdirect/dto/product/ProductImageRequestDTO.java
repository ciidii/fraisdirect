package com.fraisdirect.dto.product;

import lombok.Data;

@Data
public class ProductImageRequestDTO {
    private Long productID;
    private String imageUrl;
    private boolean principal;
}
