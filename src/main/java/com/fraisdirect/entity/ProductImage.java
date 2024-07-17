package com.fraisdirect.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductImage {
    @EmbeddedId
    private ProductImageKey productImageKey;

    @JoinColumn(name = "product_id",insertable = false,updatable = false)
    @ManyToOne
    private Product productID;

    @JoinColumn(name = "url_image",insertable = false,updatable = false)
    private String imageUrl;
    
    @JoinColumn(name = "principal",insertable = false,updatable = false)
    private boolean principal;
}
