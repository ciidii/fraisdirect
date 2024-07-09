package com.fraisdirect.service;

import com.fraisdirect.dto.price.WightBasedPriceRequestDTO;
import com.fraisdirect.dto.price.WightBasedPriceResponseDTO;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import org.springframework.http.ResponseEntity;

public interface WeighBasePriceService {
    public ResponseEntity<ResponseVO<Void>> createPrice(WightBasedPriceRequestDTO priceRequestDTO);
    public ResponseEntity<ResponsePageableVO<WightBasedPriceResponseDTO>> browserAll(RequestPageableVO requestPageableVO);
    public ResponseEntity<ResponseVO<Void>> modify(WightBasedPriceResponseDTO wightBasedPriceResponseDTO);
    public ResponseEntity<ResponseVO<Void>> delete(Long weighBasePriceID);
}
