package com.fraisdirect.service;

import com.fraisdirect.dto.ProductRequestDTO;
import com.fraisdirect.dto.ProductResponseDTO;
import com.fraisdirect.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CatalogueService {
    public ResponseEntity<ResponseVO<ProductResponseDTO>> addItem(ProductRequestDTO productRequestDTO);
}
