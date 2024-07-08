package com.fraisdirect.dto.price;

import com.fraisdirect.entity.PRICE_MODEL;
import com.fraisdirect.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductPriceModelRequestDTO {
    @NotNull
    private Long product;
    @NotNull
    private PRICE_MODEL priceModel;
    @NotNull
    private Long basedPriceID;
    @NotNull
    private boolean status;
}
