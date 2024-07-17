package com.fraisdirect.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class QuantityBasedPrice{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quantityBasedPriceID;
    @Column(unique = true)
    private String label;
    private int quantity;
    private float price;
    private AROUND around;
}
