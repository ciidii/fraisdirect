package com.fraisdirect.dto.order;

import com.fraisdirect.entity.Order;
import com.fraisdirect.entity.PRICE_MODEL;
import lombok.Data;

@Data
public class OrderDetailsResponseDTO {
    private Long orderDetailsID;
    private OrderResponseDTO orderResponseDTO;
    private double quantity;
    private PRICE_MODEL priceModel;
    private Long priceBasedModel;
}
