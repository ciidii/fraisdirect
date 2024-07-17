package com.fraisdirect.dto.order;

import com.fraisdirect.entity.ORDER_STATE;
import com.fraisdirect.entity.Utilisateur;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderRequestDTO {
    @NotNull
    private Long customerID;
    @NotNull
    private ORDER_STATE orderState;
    @NotNull
    @NotEmpty
    private List<OrderDetailsRequestDTO> orderDetails;
}
