package com.fraisdirect.mapper;


import com.fraisdirect.dto.order.OrderDetailsRequestDTO;
import com.fraisdirect.dto.order.OrderDetailsResponseDTO;
import com.fraisdirect.entity.OrderDetails;
import com.fraisdirect.entity.Product;
import com.fraisdirect.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderDetailsMapper {
    //private final OrderMapper orderMapper;
    private ProductRepository productRepository;
    public OrderDetailsResponseDTO toDto(OrderDetails orderDetails) {
        if (orderDetails == null) {
            return null;
        }

        OrderDetailsResponseDTO dto = new OrderDetailsResponseDTO();
        dto.setOrderDetailsID(orderDetails.getOderDetailsID());
       // dto.setOrderResponseDTO(this.orderMapper.toDto(orderDetails.getOrder()));
        dto.setQuantity(orderDetails.getQuantity());

        return dto;
    }


    public OrderDetails toEntity(OrderDetailsRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setQuantity(dto.getQuantity());

        Product product = productRepository.findById(dto.getProductID())
                .orElseThrow(() -> new EntityNotFoundException("Invalid product ID: " + dto.getProductID()));
        orderDetails.setProduct(product);

        return orderDetails;
    }

    public List<OrderDetails> toEntity(List<OrderDetailsRequestDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
