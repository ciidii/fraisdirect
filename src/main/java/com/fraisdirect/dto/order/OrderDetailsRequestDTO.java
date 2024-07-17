package com.fraisdirect.dto.order;

import com.fraisdirect.entity.PRICE_MODEL;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDetailsRequestDTO {
    @NotNull
    private double quantity;
    @NotNull
    private Long productID;

}
