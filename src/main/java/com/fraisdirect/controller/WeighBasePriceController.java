package com.fraisdirect.controller;

import com.fraisdirect.dto.price.WightBasedPriceRequestDTO;
import com.fraisdirect.dto.price.WightBasedPriceResponseDTO;
import com.fraisdirect.service.WeighBasePriceService;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weight-price")
@AllArgsConstructor
public class WeighBasePriceController {

    private final WeighBasePriceService weighBasePriceService;

    @PostMapping()
    public ResponseEntity<ResponseVO<Void>> createPrice(@Valid @RequestBody WightBasedPriceRequestDTO priceRequestDTO) {
        return weighBasePriceService.createPrice(priceRequestDTO);
    }

    @GetMapping("all")
    public ResponseEntity<ResponsePageableVO<WightBasedPriceResponseDTO>> browseAll(@Valid @RequestBody RequestPageableVO requestPageableVO) {
        return weighBasePriceService.browserAll(requestPageableVO);
    }

    @PutMapping()
    public ResponseEntity<ResponseVO<Void>> modify(@Valid @RequestBody WightBasedPriceResponseDTO wightBasedPriceResponseDTO) {
        return weighBasePriceService.modify(wightBasedPriceResponseDTO);
    }

    @DeleteMapping
    public ResponseEntity<ResponseVO<Void>> delete(@Positive @Min(1) @RequestParam("id") Long id) {
        return weighBasePriceService.delete(id);
    }
}
