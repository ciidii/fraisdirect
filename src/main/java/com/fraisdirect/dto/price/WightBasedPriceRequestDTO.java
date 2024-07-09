package com.fraisdirect.dto.price;

import com.fraisdirect.entity.AROUND;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WightBasedPriceRequestDTO {
    @NotNull
    private String label;
    @NotNull
    private float wight;
    @NotNull
    private float price;
    @NotNull
    private AROUND around;
}
