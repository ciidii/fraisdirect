package com.fraisdirect.service.Impl;

import com.fraisdirect.dto.price.WightBasedPriceRequestDTO;
import com.fraisdirect.dto.price.WightBasedPriceResponseDTO;
import com.fraisdirect.entity.WeightBasedPrice;
import com.fraisdirect.mapper.price.WightBasedPriceMapper;
import com.fraisdirect.repository.WeightBasedPriceRepository;
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
    private final WeightBasedPriceRepository weightBasedPriceRepository;
    private final WightBasedPriceMapper wightBasedPriceMapper;

    @Override
    public ResponseEntity<ResponseVO<Void>> createPrice(WightBasedPriceRequestDTO priceRequestDTO) {
        WeightBasedPrice weightBasedPrice = this.wightBasedPriceMapper.toEntity(priceRequestDTO);
        this.weightBasedPriceRepository.save(weightBasedPrice);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponsePageableVO<WightBasedPriceResponseDTO>> browserAll(RequestPageableVO requestPageableVO) {
        PageRequest pageRequest = PageRequest.of(requestPageableVO.getPage() - 1, requestPageableVO.getRpp());
        Page<WeightBasedPrice> wightBasedPricePage = this.weightBasedPriceRepository.findAll(pageRequest);

        List<WightBasedPriceResponseDTO> responseDTOList = new ArrayList<>();

        for (WeightBasedPrice weightBasedPrice : wightBasedPricePage) {
            responseDTOList.add(this.wightBasedPriceMapper.toDto(weightBasedPrice));
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
        this.weightBasedPriceRepository
                .findById(wightBasedPriceResponseDTO.getWightBasedPriceID())
                .orElseThrow(() -> new EntityNotFoundException("Cette entité n'existe pas"));
        WeightBasedPrice weightBasedPrice = this.wightBasedPriceMapper.toEntity(wightBasedPriceResponseDTO);
                this.weightBasedPriceRepository.save(weightBasedPrice);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> delete(Long weighBasePriceID) {
        WeightBasedPrice weightBasedPrice = this.weightBasedPriceRepository
                .findById(weighBasePriceID)
                .orElseThrow(() -> new EntityNotFoundException("Cette entité n'existe pas"));
        this.weightBasedPriceRepository.delete(weightBasedPrice);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().build(), HttpStatus.OK);
    }
}
