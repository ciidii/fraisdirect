package com.fraisdirect.dto.order;

import com.fraisdirect.entity.ORDER_STATE;
import com.fraisdirect.entity.Utilisateur;
import lombok.Data;


import java.time.LocalDate;

@Data
public class OrderResponseDTO {
    private Long orderID;
    private Integer customerID;
    private LocalDate orderDate=LocalDate.now();
    private ORDER_STATE orderState;
}
