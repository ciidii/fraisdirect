package com.fraisdirect.dto;

import lombok.Data;

@Data
public class ProductImageRequestDTO {
    private Long productID;
    private String imageUrl;
    private boolean principal;
}
