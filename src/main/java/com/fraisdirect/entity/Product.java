package com.fraisdirect.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    private String codeProduct;
    private String description;
    private float basicPrice;
    private boolean status;

    @ManyToOne(cascade = CascadeType.ALL)
    private SubCategory subCategory;
}
