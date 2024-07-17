package com.fraisdirect.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OderDetailsID;
    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "orderID")
    private Order order;
    @ManyToOne
    private Product product;
    private double quantity;
}
