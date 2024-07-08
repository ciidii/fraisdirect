package com.fraisdirect.service.Impl;

import com.fraisdirect.dto.price.QuantityBasedPriceRequestDTO;
import com.fraisdirect.dto.price.QuantityBasedPriceResponseDTO;
import com.fraisdirect.entity.QuantityBasedPrice;
import com.fraisdirect.mapper.price.QuantityBasedPriceMapper;
import com.fraisdirect.repository.QuantityBasedPriceRepository;
import com.fraisdirect.service.QuantityBasedPriceService;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import com.fraisdirect.utils.ResponseVOBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class QuantityBasedPriceServiceImpl implements QuantityBasedPriceService {
    private final QuantityBasedPriceRepository quantityBasedPriceRepository;
    private final QuantityBasedPriceMapper quantityBasedPriceMapper;
    @Override
    public ResponseEntity<ResponseVO<Void>> createPrice(QuantityBasedPriceRequestDTO priceRequestDTO) {
        QuantityBasedPrice quantityBasedPrice = this.quantityBasedPriceMapper.toEntity(priceRequestDTO);
        this.quantityBasedPriceRepository.save(quantityBasedPrice);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponsePageableVO<QuantityBasedPriceResponseDTO>> browserAll(RequestPageableVO requestPageableVO) {
        {
            PageRequest pageRequest = PageRequest.of(requestPageableVO.getPage() - 1, requestPageableVO.getRpp());
            Page<QuantityBasedPrice> categoryPage = this.quantityBasedPriceRepository.findAll(pageRequest);

            List<QuantityBasedPriceResponseDTO> memberResponseDtos = new ArrayList<>();

            for (QuantityBasedPrice category : categoryPage) {
                memberResponseDtos.add(this.quantityBasedPriceMapper.toDto(category));
            }

            ResponsePageableVO<QuantityBasedPriceResponseDTO> responsePageableVO = new ResponsePageableVO<>(
                    categoryPage.getTotalElements(),
                    memberResponseDtos,
                    requestPageableVO
            );

            return new ResponseEntity<>(responsePageableVO, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> modify(QuantityBasedPriceResponseDTO quantityBasedPriceResponseDTO) {
        this.quantityBasedPriceRepository
                .findById(quantityBasedPriceResponseDTO
                        .getQuantityBasedPriceID())
                .orElseThrow(() -> new EntityNotFoundException("Cette entité n'existe pas"));
        QuantityBasedPrice quantityBasedPrice = this.quantityBasedPriceMapper.toEntity(quantityBasedPriceResponseDTO);
                this.quantityBasedPriceRepository.save(quantityBasedPrice);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> delete(Long quantityBasedPriceID) {
        QuantityBasedPrice quantityBasedPrice = this.quantityBasedPriceRepository
                .findById(quantityBasedPriceID)
                .orElseThrow(() -> new EntityNotFoundException("Cette entité n'existe pas"));
        this.quantityBasedPriceRepository.delete(quantityBasedPrice);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().build(), HttpStatus.CREATED);
    }
}