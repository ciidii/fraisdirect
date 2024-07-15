package com.fraisdirect.service;

import com.fraisdirect.dto.DTOForAttributeSubCategory;
import com.fraisdirect.dto.SubCategoryRequestDTO;
import com.fraisdirect.dto.SubCategoryResponseDTO;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import org.springframework.http.ResponseEntity;

public interface SubCategoryService {
    public ResponseEntity<ResponseVO<SubCategoryResponseDTO>> addSubCategory(SubCategoryRequestDTO categoryRequestDTO);
    public ResponseEntity<ResponseVO<SubCategoryResponseDTO>> modifySubCategory(SubCategoryResponseDTO categoryRequestDTO);
    public ResponseEntity<ResponsePageableVO<SubCategoryResponseDTO>> browserSubCategory(RequestPageableVO requestPageableVO);
    public ResponseEntity<ResponseVO<SubCategoryResponseDTO>> getSubCategoryByID(Long categoryID);
    public ResponseEntity<ResponseVO<SubCategoryResponseDTO>> addAttributeSubCategory(DTOForAttributeSubCategory dtoForAttributeSubCategory);
    ResponseEntity<ResponsePageableVO<SubCategoryResponseDTO>> getSubCategoryByCategoryID(RequestPageableVO requestPageableVO, Long categoryID);
}
