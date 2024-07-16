package com.fraisdirect.dto.price;

import com.fraisdirect.entity.PRICE_MODEL;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductPriceDTO<T> {
    @NotNull
    private Long productPriceModelID;
    @NotNull
    private Long product;
    @NotNull
    private PRICE_MODEL priceModel;
    @NotNull
    private T basedPriceID;
    @NotNull
    private boolean status;
}
