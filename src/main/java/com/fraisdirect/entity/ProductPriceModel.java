package com.fraisdirect.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ProductPriceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productPriceModelID;
    @ManyToOne
    private Product product;
    private PRICE_MODEL priceModel;
    @Column(name = "based_price_id")
    private Long basedPriceID;
    private boolean status;
}
