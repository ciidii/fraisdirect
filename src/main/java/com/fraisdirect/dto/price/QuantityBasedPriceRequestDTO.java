package com.fraisdirect.dto.price;

import com.fraisdirect.entity.AROUND;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuantityBasedPriceRequestDTO {
    @NotNull
    private String label;
    @NotNull
    private int quantity;
    @NotNull
    private float price;
    @NotNull
    private AROUND around;
}
