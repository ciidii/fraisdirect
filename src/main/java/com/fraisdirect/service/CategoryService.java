package com.fraisdirect.service;

import com.fraisdirect.dto.CategoryRequestDTO;
import com.fraisdirect.dto.CategoryResponseDTO;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    public ResponseEntity<ResponseVO<CategoryResponseDTO>> addCategory(CategoryRequestDTO categoryRequestDTO);
    public ResponseEntity<ResponseVO<CategoryResponseDTO>> modifyCategory(CategoryResponseDTO categoryResponseDTO);
    public ResponseEntity<ResponseVO<List<CategoryResponseDTO>>> browserCategory();
    public ResponseEntity<ResponseVO<CategoryResponseDTO>> getCategoryByID(Long categoryID);
}
