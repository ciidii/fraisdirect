package com.fraisdirect.controller;

import com.fraisdirect.dto.product.ProductRequestDTO;
import com.fraisdirect.dto.product.ProductResponseDTO;
import com.fraisdirect.service.CatalogueService;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("catalogue")
@AllArgsConstructor
public class CatalogueController {
    private final CatalogueService catalogueService;

    @PostMapping()
    public ResponseEntity<ResponseVO<ProductResponseDTO>> addItem(@ModelAttribute ProductRequestDTO productRequestDT) {
        return this.catalogueService.addItem(productRequestDT);
    }

    @GetMapping("all")
    public ResponseEntity<ResponsePageableVO<ProductResponseDTO>> browser(@Min(1) @RequestParam("page") int page, @Min(1) @RequestParam("rpp") int rpp, @Min(0) @Max(2) @RequestParam(value = "status", defaultValue = "0") int status) {
        RequestPageableVO requestPageableVO = new RequestPageableVO(page, rpp);
        return this.catalogueService.browser(requestPageableVO, status);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        this.catalogueService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseVO<ProductResponseDTO>> updateItem(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        return this.catalogueService.updateItem(id, productRequestDTO);
    }


    @GetMapping("product-by-subcategory")
    public ResponseEntity<ResponsePageableVO<ProductResponseDTO>> browserProductBySubcategory(@Min(1) @RequestParam("page") int page, @Min(1) @RequestParam("rpp") int rpp, @Min(1) @RequestParam(value = "subcategoryID") Long subcategoryID) {
        RequestPageableVO requestPageableVO = new RequestPageableVO(page, rpp);
        return this.catalogueService.browserProductBySubcategory(requestPageableVO, subcategoryID);
    }

    @GetMapping
    public ResponseEntity<ResponseVO<ProductResponseDTO>> getProductById(@RequestParam("productID") @Min(1) @Positive Long productID) {
        return this.catalogueService.getProductById(productID);
    }
}
