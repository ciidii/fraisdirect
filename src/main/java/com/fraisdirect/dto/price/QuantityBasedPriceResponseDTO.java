package com.fraisdirect.dto.price;

import com.fraisdirect.entity.AROUND;
import lombok.Data;

@Data
public class QuantityBasedPriceResponseDTO {
    private Long quantityBasedPriceID;
    private String label;
    private int quantity;
    private float price;
    private AROUND around;
}
