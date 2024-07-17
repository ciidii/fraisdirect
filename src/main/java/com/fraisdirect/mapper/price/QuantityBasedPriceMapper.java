package com.fraisdirect.mapper.price;

import com.fraisdirect.dto.price.QuantityBasedPriceRequestDTO;
import com.fraisdirect.dto.price.QuantityBasedPriceResponseDTO;
import com.fraisdirect.entity.QuantityBasedPrice;
import org.springframework.stereotype.Component;

@Component
public class QuantityBasedPriceMapper {
    public QuantityBasedPrice toEntity(QuantityBasedPriceRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        QuantityBasedPrice entity = new QuantityBasedPrice();
        entity.setLabel(dto.getLabel());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        entity.setAround(dto.getAround());

        return entity;
    }
    public QuantityBasedPrice toEntity(QuantityBasedPriceResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        QuantityBasedPrice entity = new QuantityBasedPrice();
        entity.setQuantityBasedPriceID(dto.getQuantityBasedPriceID());
        entity.setLabel(dto.getLabel());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        entity.setAround(dto.getAround());

        return entity;
    }

    public QuantityBasedPriceResponseDTO toDto(QuantityBasedPrice entity) {
        if (entity == null) {
            return null;
        }

        QuantityBasedPriceResponseDTO dto = new QuantityBasedPriceResponseDTO();
        dto.setQuantityBasedPriceID(entity.getQuantityBasedPriceID());
        dto.setLabel(entity.getLabel());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        dto.setAround(entity.getAround());

        return dto;
    }

    public QuantityBasedPriceResponseDTO toDtoResponse(QuantityBasedPrice entity) {
        if (entity == null) {
            return null;
        }
        QuantityBasedPriceResponseDTO dto = new QuantityBasedPriceResponseDTO();
        dto.setQuantityBasedPriceID(entity.getQuantityBasedPriceID());
        dto.setLabel(entity.getLabel());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        dto.setAround(entity.getAround());
        return dto;
    }

}
