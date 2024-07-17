package com.fraisdirect.mapper.price;

import com.fraisdirect.dto.price.ProductPriceDTO;
import com.fraisdirect.entity.ProductPriceModel;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceMapper {

    public <T>   ProductPriceDTO<T> toDTO(ProductPriceModel model,T t) {
        if (model == null) {
            return null;
        }

        ProductPriceDTO<T> dto = new ProductPriceDTO<T>();
        dto.setProductPriceModelID(model.getProductPriceModelID());
        dto.setProduct(model.getProduct().getProductID());
        dto.setPriceModel(model.getPriceModel());
        dto.setBasedPriceID(t);
        dto.setStatus(model.isStatus());

        return dto;
    }
}
