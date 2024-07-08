package com.fraisdirect.mapper.price;

import com.fraisdirect.dto.price.ProductPriceModelRequestDTO;
import com.fraisdirect.dto.price.ProductPriceModelResponseDTO;
import com.fraisdirect.entity.ProductPriceModel;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceModelMapper {
    public ProductPriceModel toEntity(ProductPriceModelRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductPriceModel entity = new ProductPriceModel();
        entity.setPriceModel(dto.getPriceModel());
        entity.setBasedPriceID(dto.getBasedPriceID());
        entity.setStatus(dto.isStatus());

        return entity;
    }
    public ProductPriceModel toEntity(ProductPriceModelResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductPriceModel entity = new ProductPriceModel();
        entity.setProductPriceModelID(dto.getProductPriceModelID());
       // entity.setProduct(dto.getProduct());
        entity.setPriceModel(dto.getPriceModel());
        entity.setBasedPriceID(dto.getBasedPriceID());
        entity.setStatus(dto.isStatus());

        return entity;
    }


    public ProductPriceModelResponseDTO toDto(ProductPriceModel entity) {
        if (entity == null) {
            return null;
        }

        ProductPriceModelResponseDTO dto = new ProductPriceModelResponseDTO();
        dto.setProductPriceModelID(entity.getProductPriceModelID());
       dto.setProduct(entity.getProduct().getProductID());
        dto.setPriceModel(entity.getPriceModel());
        dto.setBasedPriceID(entity.getBasedPriceID());
        dto.setStatus(entity.isStatus());
        return dto;
    }

}
