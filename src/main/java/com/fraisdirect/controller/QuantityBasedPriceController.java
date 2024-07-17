package com.fraisdirect.controller;

import com.fraisdirect.dto.price.QuantityBasedPriceRequestDTO;
import com.fraisdirect.dto.price.QuantityBasedPriceResponseDTO;
import com.fraisdirect.service.QuantityBasedPriceService;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quantity-base-pricing")
@AllArgsConstructor
public class QuantityBasedPriceController {

    private final QuantityBasedPriceService quantityBasedPriceService;

    @PostMapping
    public ResponseEntity<ResponseVO<Void>> createPrice(@Valid @RequestBody QuantityBasedPriceRequestDTO priceRequestDTO) {
        return quantityBasedPriceService.createPrice(priceRequestDTO);
    }

    @GetMapping("all")
    public ResponseEntity<ResponsePageableVO<QuantityBasedPriceResponseDTO>> browseAll(@RequestBody RequestPageableVO requestPageableVO) {
        return quantityBasedPriceService.browserAll(requestPageableVO);
    }

    @PutMapping
    public ResponseEntity<ResponseVO<Void>> modify(@Valid @RequestBody QuantityBasedPriceResponseDTO quantityBasedPriceResponseDTO) {
        return quantityBasedPriceService.modify(quantityBasedPriceResponseDTO);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseVO<Void>> delete(@RequestParam("id") Long id) {
        return quantityBasedPriceService.delete(id);
    }
}
