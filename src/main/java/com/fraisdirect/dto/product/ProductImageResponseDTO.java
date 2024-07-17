package com.fraisdirect.dto.product;

import lombok.Data;

@Data
public class ProductImageResponseDTO {
    private byte[] imageUrl;
    private boolean principal;
}
