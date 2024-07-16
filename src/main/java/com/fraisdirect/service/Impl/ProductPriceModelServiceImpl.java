package com.fraisdirect.service.Impl;

import com.fraisdirect.dto.price.ProductPriceDTO;
import com.fraisdirect.dto.price.ProductPriceModelRequestDTO;
import com.fraisdirect.dto.price.ProductPriceModelResponseDTO;
import com.fraisdirect.entity.*;
import com.fraisdirect.mapper.price.ProductPriceMapper;
import com.fraisdirect.mapper.price.ProductPriceModelMapper;
import com.fraisdirect.repository.ProductPriceModelRepository;
import com.fraisdirect.repository.ProductRepository;
import com.fraisdirect.repository.QuantityBasedPriceRepository;
import com.fraisdirect.repository.WightBasedPriceRepository;
import com.fraisdirect.service.ProductPriceModelService;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductPriceModelServiceImpl implements ProductPriceModelService {
    private final ProductPriceModelRepository productPriceModelRepository;
    private final ProductPriceModelMapper productPriceModelMapper;
    private final ProductRepository productRepository;
    private final WightBasedPriceRepository wightBasedPriceRepository;
    private final QuantityBasedPriceRepository quantityBasedPriceRepository;
    private final ProductPriceMapper productPriceMapper;

    @Override
    @Transactional
    public ResponseEntity<ResponseVO<Void>> createPrice(ProductPriceModelRequestDTO priceRequestDTO) {
        Product product = this.productRepository.
                findById(priceRequestDTO.getProduct()).
                orElseThrow(() -> new EntityNotFoundException("Ce produit n'exist pas"));
        ProductPriceModel priceModel = this.productPriceModelMapper.toEntity(priceRequestDTO);
        priceModel.setProduct(product);

        if (this.productPriceModelRepository.findActivePrice(priceRequestDTO.getProduct()).isPresent()) {
            ProductPriceModel ppm = this.productPriceModelRepository.findActivePrice(priceRequestDTO.getProduct()).orElseThrow();
            ppm.setStatus(false);
            this.productPriceModelRepository.save(ppm);
        }
        if (priceRequestDTO.getPriceModel() == PRICE_MODEL.QUANTITY) {
            this.quantityBasedPriceRepository.findById(priceRequestDTO.getBasedPriceID()).orElseThrow(() -> new EntityNotFoundException("Il n'existe pas un modèle de prix basé sur quantité  avec un identifiant" + priceRequestDTO.getBasedPriceID()));
        } else if (priceRequestDTO.getPriceModel() == PRICE_MODEL.WEIGHT) {
            this.wightBasedPriceRepository.findById(priceRequestDTO.getBasedPriceID()).orElseThrow(() -> new EntityNotFoundException("Il n'existe pas un modèle de prix basé sur le poid  avec un identifiant " + priceRequestDTO.getBasedPriceID()));
        }
        this.productPriceModelRepository.save(priceModel);
        product.setStatus(true);
        this.productRepository.save(product);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponsePageableVO<ProductPriceModelResponseDTO>> browserAll(RequestPageableVO requestPageableVO) {
        PageRequest pageRequest = PageRequest.of(requestPageableVO.getPage() - 1, requestPageableVO.getRpp());
        Page<ProductPriceModel> categoryPage = this.productPriceModelRepository.findAll(pageRequest);

        List<ProductPriceModelResponseDTO> memberResponseDtos = new ArrayList<>();

        for (ProductPriceModel category : categoryPage) {
            memberResponseDtos.add(this.productPriceModelMapper.toDto(category));
        }

        ResponsePageableVO<ProductPriceModelResponseDTO> responsePageableVO = new ResponsePageableVO<>(
                categoryPage.getTotalElements(),
                memberResponseDtos,
                requestPageableVO
        );

        return new ResponseEntity<>(responsePageableVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> modify(ProductPriceModelResponseDTO productPriceModelResponseDTO) {
        ProductPriceModel priceModel = this.productPriceModelMapper.toEntity(productPriceModelResponseDTO);
        this.productPriceModelRepository.save(priceModel);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> delete(Long productPriceModelID) {
        ProductPriceModel priceModel = this.productPriceModelRepository.findById(productPriceModelID).orElseThrow(() -> new EntityNotFoundException("Cette entité n'existe pas"));
        this.productPriceModelRepository.delete(priceModel);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<ProductPriceDTO<?>>> getProductActivePriceHelper(Long productID) {
        this.productRepository.findById(productID).orElseThrow(() -> new EntityNotFoundException("Il existe pas un product un ID " + productID));
        ProductPriceModel activePrice = this.productPriceModelRepository.findActivePrice(productID).orElseThrow();
        ProductPriceDTO<?> productPriceDTO = new ProductPriceDTO<>();
        if (activePrice.getPriceModel().toString().equals("WEIGHT")) {
            WightBasedPrice wightBasedPrice = wightBasedPriceRepository.findById(activePrice.getBasedPriceID()).orElseThrow();
            productPriceDTO= this.productPriceMapper.toDTO(activePrice,wightBasedPrice);

        } else if (activePrice.getPriceModel().toString().equals("QUANTITY")) {
            QuantityBasedPrice quantityBasedPrice = this.quantityBasedPriceRepository.findById(activePrice.getBasedPriceID()).orElseThrow();
            productPriceDTO =this.productPriceMapper.toDTO(activePrice,quantityBasedPrice);
        }
        ResponseVO<ProductPriceDTO<?>> productPriceDTOResponseVO = new ResponseVOBuilder<ProductPriceDTO<?>>().addData(productPriceDTO).build();
        return new ResponseEntity<>(productPriceDTOResponseVO,HttpStatus.OK);
    }
}
