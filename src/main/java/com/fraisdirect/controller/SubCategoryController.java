package com.fraisdirect.controller;

import com.fraisdirect.dto.DTOForAttributeSubCategory;
import com.fraisdirect.dto.SubCategoryRequestDTO;
import com.fraisdirect.dto.SubCategoryResponseDTO;
import com.fraisdirect.service.SubCategoryService;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subcategories")
@AllArgsConstructor
@Validated
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    @PostMapping
    public ResponseEntity<ResponseVO<SubCategoryResponseDTO>> addSubCategory(@Valid @RequestBody SubCategoryRequestDTO subCategoryRequestDTO) {
        return this.subCategoryService.addSubCategory(subCategoryRequestDTO);
    }
    @PostMapping("attributes")
    public ResponseEntity<ResponseVO<SubCategoryResponseDTO>> addAttributes(@Valid @RequestBody DTOForAttributeSubCategory dtoForAttributeSubCategory){
        return this.subCategoryService.addAttributeSubCategory(dtoForAttributeSubCategory);
    }
    @PutMapping
    public ResponseEntity<ResponseVO<SubCategoryResponseDTO>> modifySubCategory(@Valid @RequestBody SubCategoryResponseDTO subCategoryResponseDTO) {
        return this.subCategoryService.modifySubCategory(subCategoryResponseDTO);
    }

    @GetMapping("all")
    public ResponseEntity<ResponseVO<List<SubCategoryResponseDTO>>> browserSubCategories() {
        return this.subCategoryService.browserSubCategory();
    }

    @GetMapping()
    public ResponseEntity<ResponseVO<SubCategoryResponseDTO>> getSubCategoryById(@Min(1) @RequestParam("subCategoryID") Long subCategoryID) {
        return this.subCategoryService.getSubCategoryByID(subCategoryID);
    }
        @GetMapping("by-category-id")
    public ResponseEntity<ResponsePageableVO<SubCategoryResponseDTO>>  getSubCategoryByCategoryID(@Min(1) @RequestParam("categoryID") Long categoryID, @RequestParam("page") int page, @RequestParam("rpp") int rpp) {
        RequestPageableVO requestPageableVO = new RequestPageableVO(page, rpp);
        return this.subCategoryService.getSubCategoryByCategoryID(requestPageableVO,categoryID);
    }
}
