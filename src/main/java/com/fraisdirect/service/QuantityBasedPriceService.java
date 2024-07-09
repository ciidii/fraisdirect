package com.fraisdirect.service;

import com.fraisdirect.dto.price.QuantityBasedPriceRequestDTO;
import com.fraisdirect.dto.price.QuantityBasedPriceResponseDTO;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import org.springframework.http.ResponseEntity;

public interface QuantityBasedPriceService {
    public ResponseEntity<ResponseVO<Void>> createPrice(QuantityBasedPriceRequestDTO priceRequestDTO);
    public ResponseEntity<ResponsePageableVO<QuantityBasedPriceResponseDTO>> browserAll(RequestPageableVO requestPageableVO);
    public ResponseEntity<ResponseVO<Void>> modify(QuantityBasedPriceResponseDTO quantityBasedPriceResponseDTO);
    public ResponseEntity<ResponseVO<Void>> delete(Long quantityBasedPriceID);
}
