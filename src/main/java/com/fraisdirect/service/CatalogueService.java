package com.fraisdirect.service;

import com.fraisdirect.dto.product.ProductRequestDTO;
import com.fraisdirect.dto.product.ProductResponseDTO;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CatalogueService {
    public ResponseEntity<ResponseVO<ProductResponseDTO>> addItem(ProductRequestDTO productRequestDTO);
    public ResponseEntity<ResponsePageableVO<ProductResponseDTO>> browser(RequestPageableVO requestPageableVO,int status);
    public ResponseEntity<ResponseVO<ProductResponseDTO>> getProductById(Long productID);
    public ResponseEntity<ResponsePageableVO<ProductResponseDTO>> findProductsByDescription(String description);
    public ResponseEntity<ResponseVO<Void>> removeProduct(Long productID);
}
