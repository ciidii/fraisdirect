package com.fraisdirect.mapper;

import com.fraisdirect.dto.order.OrderRequestDTO;
import com.fraisdirect.dto.order.OrderResponseDTO;
import com.fraisdirect.entity.Order;
import com.fraisdirect.entity.OrderDetails;
import com.fraisdirect.entity.Utilisateur;
import com.fraisdirect.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderMapper {
    private OrderDetailsMapper orderDetailsMapper;
    private UtilisateurRepository utilisateurRepository;

    public OrderResponseDTO toDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderID(order.getOrderID());
        dto.setCustomerID(order.getCustomerID().getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setOrderState(order.getOrderState());

        return dto;
    }

    public Order toEntity(OrderRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Order order = new Order();
        Utilisateur utilisateur = this.utilisateurRepository.findById(Math.toIntExact(dto.getCustomerID())).orElseThrow(() -> new EntityNotFoundException("Il Créer un compter avant de passer une commande"));
        order.setCustomerID(utilisateur);
        order.setOrderState(dto.getOrderState());

        List<OrderDetails> orderDetailsList = orderDetailsMapper.toEntity(dto.getOrderDetails());
        orderDetailsList.forEach(orderDetails -> orderDetails.setOrder(order));
        order.setOrderDetails(orderDetailsList);

        return order;
    }
}
