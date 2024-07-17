package com.fraisdirect.service.Impl;

import com.fraisdirect.dto.order.OrderRequestDTO;
import com.fraisdirect.dto.order.OrderResponseDTO;
import com.fraisdirect.entity.Order;
import com.fraisdirect.mapper.OrderMapper;
import com.fraisdirect.repository.OrderRepository;
import com.fraisdirect.service.OrderService;
import com.fraisdirect.utils.ResponseVO;
import com.fraisdirect.utils.ResponseVOBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    @Override
    public ResponseEntity<ResponseVO<OrderResponseDTO>> order(OrderRequestDTO orderRequestDTO) {
        Order order = this.orderMapper.toEntity(orderRequestDTO);
       order = this.orderRepository.save(order);
       OrderResponseDTO orderResponseDTO = this.orderMapper.toDto(order);
        ResponseVO<OrderResponseDTO> responseVO =  new ResponseVOBuilder<OrderResponseDTO>().addData(orderResponseDTO).build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }
}
