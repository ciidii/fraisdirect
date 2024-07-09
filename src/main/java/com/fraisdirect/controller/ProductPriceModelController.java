package com.fraisdirect.controller;


import com.fraisdirect.dto.price.ProductPriceModelRequestDTO;
import com.fraisdirect.dto.price.ProductPriceModelResponseDTO;
import com.fraisdirect.service.ProductPriceModelService;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product-price")
@AllArgsConstructor
public class ProductPriceModelController {

    private final ProductPriceModelService productPriceModelService;

    @PostMapping
    public ResponseEntity<ResponseVO<Void>> createPrice( @Valid @RequestBody ProductPriceModelRequestDTO productPriceModelDTO) {
        return productPriceModelService.createPrice(productPriceModelDTO);
    }

    @GetMapping("all")
    public ResponseEntity<ResponsePageableVO<ProductPriceModelResponseDTO>> browseAll(@RequestBody RequestPageableVO requestPageableVO) {
        return productPriceModelService.browserAll(requestPageableVO);
    }

    @PutMapping
    public ResponseEntity<ResponseVO<Void>> modify(@RequestBody ProductPriceModelResponseDTO productPriceModelDTO) {
        return productPriceModelService.modify(productPriceModelDTO);
    }

    @DeleteMapping
    public ResponseEntity<ResponseVO<Void>> delete(@RequestParam("id") Long id) {
        return productPriceModelService.delete(id);
    }
}
