package com.fraisdirect.mapper.price;

import com.fraisdirect.dto.price.WightBasedPriceRequestDTO;
import com.fraisdirect.dto.price.WightBasedPriceResponseDTO;
import com.fraisdirect.entity.WeightBasedPrice;
import org.springframework.stereotype.Component;

@Component
public class WightBasedPriceMapper {
    public WeightBasedPrice toEntity(WightBasedPriceRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        WeightBasedPrice entity = new WeightBasedPrice();
        entity.setLabel(dto.getLabel());
        entity.setWight(dto.getWight());
        entity.setPrice(dto.getPrice());
        entity.setAround(dto.getAround());

        return entity;
    }public WeightBasedPrice toEntity(WightBasedPriceResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        WeightBasedPrice entity = new WeightBasedPrice();
        entity.setWightBasedPriceID(dto.getWightBasedPriceID());
        entity.setLabel(dto.getLabel());
        entity.setWight(dto.getWight());
        entity.setPrice(dto.getPrice());
        entity.setAround(dto.getAround());

        return entity;
    }

    public WightBasedPriceResponseDTO toDto(WeightBasedPrice entity) {
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

    public WightBasedPriceResponseDTO toDtoResponse(WeightBasedPrice entity) {
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
