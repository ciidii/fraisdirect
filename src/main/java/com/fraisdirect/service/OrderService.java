package com.fraisdirect.service;

import com.fraisdirect.dto.order.OrderRequestDTO;
import com.fraisdirect.dto.order.OrderResponseDTO;
import com.fraisdirect.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    public ResponseEntity<ResponseVO<OrderResponseDTO>> order(OrderRequestDTO orderRequestDTO);
}
