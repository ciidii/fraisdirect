package com.fraisdirect.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Embeddable
@Data
@EqualsAndHashCode
public class ProductImageKey implements Serializable {
    @Column(name = "product_id")
    private Long productID;
    @Column(name = "url_image")
    private String imageUrl;
    @Column(name = "pricipal")
    private boolean principal;

}
