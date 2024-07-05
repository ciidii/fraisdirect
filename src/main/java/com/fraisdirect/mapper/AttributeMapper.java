package com.fraisdirect.mapper;

import com.fraisdirect.dto.AttributeResponseDTO;
import com.fraisdirect.entity.Attribute;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AttributeMapper {

    public AttributeResponseDTO toDto(Attribute attribute) {
        if (attribute == null) {
            return null;
        }
        AttributeResponseDTO dto = new AttributeResponseDTO();
        dto.setAttributeID(attribute.getAttributeID());
        dto.setAttributeName(attribute.getAttributeName());
        dto.setAttributeDescription(attribute.getAttributeDescription());
        return dto;
    }

    public Attribute toEntity(AttributeResponseDTO dto) {
        if (dto == null) {
            return null;
        }
        Attribute attribute = new Attribute();
        attribute.setAttributeID(dto.getAttributeID());
        attribute.setAttributeName(dto.getAttributeName());
        attribute.setAttributeDescription(dto.getAttributeDescription());
        return attribute;
    }

    public List<AttributeResponseDTO> toDtoList(List<Attribute> attributes) {
        if (attributes == null) {
            return null;
        }
        return attributes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Attribute> toEntityList(List<AttributeResponseDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
