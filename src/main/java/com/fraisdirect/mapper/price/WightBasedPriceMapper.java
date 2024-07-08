package com.fraisdirect.mapper.price;

import com.fraisdirect.dto.price.WightBasedPriceRequestDTO;
import com.fraisdirect.dto.price.WightBasedPriceResponseDTO;
import com.fraisdirect.entity.WightBasedPrice;
import org.springframework.stereotype.Component;

@Component
public class WightBasedPriceMapper {
    public WightBasedPrice toEntity(WightBasedPriceRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        WightBasedPrice entity = new WightBasedPrice();
        entity.setLabel(dto.getLabel());
        entity.setWight(dto.getWight());
        entity.setPrice(dto.getPrice());
        entity.setAround(dto.getAround());

        return entity;
    }public WightBasedPrice toEntity(WightBasedPriceResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        WightBasedPrice entity = new WightBasedPrice();
        entity.setWightBasedPriceID(dto.getWightBasedPriceID());
        entity.setLabel(dto.getLabel());
        entity.setWight(dto.getWight());
        entity.setPrice(dto.getPrice());
        entity.setAround(dto.getAround());

        return entity;
    }

    public WightBasedPriceResponseDTO toDto(WightBasedPrice entity) {
        if (entity == null) {
            return null;
        }

        WightBasedPriceResponseDTO dto = new WightBasedPriceResponseDTO();
        dto.setWightBasedPriceID(entity.getWightBasedPriceID());
        dto.setLabel(entity.getLabel());
        dto.setWight(entity.getWight());
        dto.setPrice(entity.getPrice());
        dto.setAround(entity.getAround());

        return dto;
    }

    public WightBasedPriceResponseDTO toDtoResponse(WightBasedPrice entity) {
        if (entity == null) {
            return null;
        }

        WightBasedPriceResponseDTO dto = new WightBasedPriceResponseDTO();
        dto.setWightBasedPriceID(entity.getWightBasedPriceID());
        dto.setLabel(entity.getLabel());
        dto.setWight(entity.getWight());
        dto.setPrice(entity.getPrice());
        dto.setAround(entity.getAround());

        return dto;
    }

}
