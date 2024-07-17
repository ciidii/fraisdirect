package com.fraisdirect.controller;

import com.fraisdirect.dto.order.OrderRequestDTO;
import com.fraisdirect.dto.order.OrderResponseDTO;
import com.fraisdirect.service.OrderService;
import com.fraisdirect.utils.ResponseVO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
@Validated
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping()
    ResponseEntity<ResponseVO<OrderResponseDTO>> order(@Valid @RequestBody OrderRequestDTO orderRequestDTO){
        return this.orderService.order(orderRequestDTO);
    }
}
