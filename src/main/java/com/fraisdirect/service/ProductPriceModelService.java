package com.fraisdirect.service;

import com.fraisdirect.dto.price.ProductPriceModelRequestDTO;
import com.fraisdirect.dto.price.ProductPriceModelResponseDTO;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductPriceModelService {
    public ResponseEntity<ResponseVO<Void>> createPrice(ProductPriceModelRequestDTO priceRequestDTO);
    public ResponseEntity<ResponsePageableVO<ProductPriceModelResponseDTO>> browserAll(RequestPageableVO requestPageableVO);
    public ResponseEntity<ResponseVO<Void>> modify(ProductPriceModelResponseDTO productPriceModelResponseDTO);
    public ResponseEntity<ResponseVO<Void>> delete(Long productPriceModelID);
}
