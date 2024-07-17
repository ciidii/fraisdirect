package com.fraisdirect.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class WeightBasedPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wightBasedPriceID;
    @Column(unique = true)
    private String label;
    private float wight;
    private float price;
    private AROUND around;
}
