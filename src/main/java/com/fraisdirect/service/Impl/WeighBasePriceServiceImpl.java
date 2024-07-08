package com.fraisdirect.service.Impl;

import com.fraisdirect.dto.price.WightBasedPriceRequestDTO;
import com.fraisdirect.dto.price.WightBasedPriceResponseDTO;
import com.fraisdirect.entity.WightBasedPrice;
import com.fraisdirect.mapper.price.WightBasedPriceMapper;
import com.fraisdirect.repository.WightBasedPriceRepository;
import com.fraisdirect.service.WeighBasePriceService;
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
public class WeighBasePriceServiceImpl implements WeighBasePriceService {
    private final WightBasedPriceRepository wightBasedPriceRepository;
    private final WightBasedPriceMapper wightBasedPriceMapper;

    @Override
    public ResponseEntity<ResponseVO<Void>> createPrice(WightBasedPriceRequestDTO priceRequestDTO) {
        WightBasedPrice wightBasedPrice = this.wightBasedPriceMapper.toEntity(priceRequestDTO);
        this.wightBasedPriceRepository.save(wightBasedPrice);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponsePageableVO<WightBasedPriceResponseDTO>> browserAll(RequestPageableVO requestPageableVO) {
        PageRequest pageRequest = PageRequest.of(requestPageableVO.getPage() - 1, requestPageableVO.getRpp());
        Page<WightBasedPrice> wightBasedPricePage = this.wightBasedPriceRepository.findAll(pageRequest);

        List<WightBasedPriceResponseDTO> responseDTOList = new ArrayList<>();

        for (WightBasedPrice wightBasedPrice : wightBasedPricePage) {
            responseDTOList.add(this.wightBasedPriceMapper.toDto(wightBasedPrice));
        }

        ResponsePageableVO<WightBasedPriceResponseDTO> responsePageableVO = new ResponsePageableVO<>(
                wightBasedPricePage.getTotalElements(),
                responseDTOList,
                requestPageableVO
        );

        return new ResponseEntity<>(responsePageableVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> modify(WightBasedPriceResponseDTO wightBasedPriceResponseDTO) {
        this.wightBasedPriceRepository
                .findById(wightBasedPriceResponseDTO.getWightBasedPriceID())
                .orElseThrow(() -> new EntityNotFoundException("Cette entité n'existe pas"));
        WightBasedPrice wightBasedPrice = this.wightBasedPriceMapper.toEntity(wightBasedPriceResponseDTO);
                this.wightBasedPriceRepository.save(wightBasedPrice);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> delete(Long weighBasePriceID) {
        WightBasedPrice wightBasedPrice = this.wightBasedPriceRepository
                .findById(weighBasePriceID)
                .orElseThrow(() -> new EntityNotFoundException("Cette entité n'existe pas"));
        this.wightBasedPriceRepository.delete(wightBasedPrice);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().build(), HttpStatus.OK);
    }
}
