package com.fraisdirect.mapper;

import com.fraisdirect.dto.ProductImageRequestDTO;
import com.fraisdirect.dto.ProductImageResponseDTO;
import com.fraisdirect.entity.ProductImageKey;
import com.fraisdirect.entity.ProductImage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductImageMapper {

    public ProductImageKey toEntity(ProductImageRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductImageKey entityKey = new ProductImageKey();
        entityKey.setProductID(dto.getProductID());
        entityKey.setImageUrl(dto.getImageUrl());
        entityKey.setPrincipal(dto.isPrincipal());
        return entityKey;
    }

    public ProductImageRequestDTO toDto(ProductImage productImage) {
        if (productImage == null) {
            return null;
        }
        ProductImageRequestDTO dto = new ProductImageRequestDTO();
        //dto.setProductID(productImage.getProductID());
        dto.setImageUrl(productImage.getImageUrl());
        dto.setPrincipal(productImage.isPrincipal());
        return dto;
    }
    public ProductImageResponseDTO toDtoResponse(ProductImageKey productImage) {
        if (productImage == null) {
            return null;
        }
        ProductImageResponseDTO dto = new ProductImageResponseDTO();
        dto.setImageUrl(productImage.getImageUrl());
        dto.setPrincipal(productImage.isPrincipal());
        return dto;
    }


    public List<ProductImageRequestDTO> toDtoList(List<ProductImage> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<ProductImageResponseDTO> toDtoResponse(List<ProductImageKey> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDtoResponse).collect(Collectors.toList());
    }
}
