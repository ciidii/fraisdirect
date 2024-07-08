package com.fraisdirect.service;

import com.fraisdirect.dto.AttributeRequestDTO;
import com.fraisdirect.dto.AttributeResponseDTO;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import org.springframework.http.ResponseEntity;

public interface AttributeService {
    public ResponseEntity<ResponseVO<AttributeResponseDTO>> addAttribute(AttributeRequestDTO attributeRequestDTO);
    public ResponseEntity<ResponseVO<AttributeResponseDTO>> modifyAttribute(AttributeResponseDTO attributeRequestDTO);
    public ResponseEntity<ResponsePageableVO<AttributeResponseDTO>> browserAttribute(RequestPageableVO requestPageableVO);
    public ResponseEntity<ResponseVO<AttributeResponseDTO>> getAttributeByID(Long attributeID);
}
