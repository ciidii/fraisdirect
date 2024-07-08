package com.fraisdirect.controller;

import com.fraisdirect.dto.CategoryRequestDTO;
import com.fraisdirect.dto.CategoryResponseDTO;
import com.fraisdirect.service.CategoryService;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
@AllArgsConstructor
@Validated
public class CategoriesController {
    private final CategoryService categoryService;

    @PostMapping
    ResponseEntity<ResponseVO<CategoryResponseDTO>> addCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return this.categoryService.addCategory(categoryRequestDTO);
    }

    @PutMapping
    ResponseEntity<ResponseVO<CategoryResponseDTO>> modifyCategory(@Valid @RequestBody CategoryResponseDTO categoryResponseDTO) {
        return this.categoryService.modifyCategory(categoryResponseDTO);
    }

    @GetMapping("all")
    public ResponseEntity<ResponsePageableVO<CategoryResponseDTO>> browserCategories(@Min(1) @RequestParam("page") int page,@Min(1) @RequestParam("rpp") int rpp){
        RequestPageableVO requestPageableVO = new RequestPageableVO(page,rpp);
        return this.categoryService.browserCategory(requestPageableVO);
    }

    @GetMapping()
    public ResponseEntity<ResponseVO<CategoryResponseDTO>> getCategoryById(@Min(1) @RequestParam("categoryID") Long categoryID){
        return this.categoryService.getCategoryByID(categoryID);
    }
}
