package com.fraisdirect.controller;

import com.fraisdirect.dto.AttributeRequestDTO;
import com.fraisdirect.dto.AttributeResponseDTO;
import com.fraisdirect.service.AttributeService;
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
@RequestMapping("attributes")
@AllArgsConstructor
@Validated
public class AttributeController {
    private final AttributeService attributeService;

    @PostMapping
    ResponseEntity<ResponseVO<AttributeResponseDTO>> addAttribute(@Valid @RequestBody AttributeRequestDTO attributeRequestDTO) {
        return this.attributeService.addAttribute(attributeRequestDTO);
    }

    @PutMapping
    ResponseEntity<ResponseVO<AttributeResponseDTO>> modifyAttribute(@Valid @RequestBody AttributeResponseDTO attributeResponseDTO) {
        return this.attributeService.modifyAttribute(attributeResponseDTO);
    }

    @GetMapping("all")
    public ResponseEntity<ResponsePageableVO<AttributeResponseDTO>> browserAttributes(@Min(1) @RequestParam("page") int page, @Min(1) @RequestParam("rpp") int rpp) {
        RequestPageableVO requestPageableVO = new RequestPageableVO(page, rpp);
        return this.attributeService.browserAttribute(requestPageableVO);
    }

    @GetMapping
    public ResponseEntity<ResponseVO<AttributeResponseDTO>> getAttributeById(@Min(1) @RequestParam("attributeID") Long attributeID) {
        return this.attributeService.getAttributeByID(attributeID);
    }
}
