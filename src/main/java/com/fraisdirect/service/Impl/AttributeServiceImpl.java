package com.fraisdirect.service.Impl;

import com.fraisdirect.dto.AttributeRequestDTO;
import com.fraisdirect.dto.AttributeResponseDTO;
import com.fraisdirect.entity.Attribute;
import com.fraisdirect.mapper.AttributeMapper;
import com.fraisdirect.repository.AttributeRepository;
import com.fraisdirect.service.AttributeService;
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
public class AttributeServiceImpl implements AttributeService {
    private final AttributeRepository attributeRepository;
    private final AttributeMapper attributeMapper;

    @Override
    public ResponseEntity<ResponseVO<AttributeResponseDTO>> addAttribute(AttributeRequestDTO attributeRequestDTO) {
        Attribute attribute = this.attributeMapper.toEntity(attributeRequestDTO);
        attribute = this.attributeRepository.save(attribute);
        AttributeResponseDTO attributeResponseDTO = this.attributeMapper.toDto(attribute);
        return new ResponseEntity<>(this.successResponseMaker(attributeResponseDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<AttributeResponseDTO>> modifyAttribute(AttributeResponseDTO attributeResponseDTO) {
            checkIfAttributeExistByAttributeID(attributeResponseDTO.getAttributeID());
        Attribute attribute = this.attributeMapper.toEntity(attributeResponseDTO);
        attribute = this.attributeRepository.save(attribute);
        AttributeResponseDTO dto = this.attributeMapper.toDto(attribute);
        return new ResponseEntity<>(this.successResponseMaker(dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePageableVO<AttributeResponseDTO>> browserAttribute(RequestPageableVO requestPageableVO) {
        PageRequest pageRequest = PageRequest.of(requestPageableVO.getPage() - 1, requestPageableVO.getRpp());
        Page<Attribute> attributePage = this.attributeRepository.findAll(pageRequest);

        List<AttributeResponseDTO> attributeResponseDTOs = new ArrayList<>();

        for (Attribute attribute : attributePage) {
            attributeResponseDTOs.add(this.attributeMapper.toDto(attribute));
        }

        ResponsePageableVO<AttributeResponseDTO> responsePageableVO = new ResponsePageableVO<>(
                attributePage.getTotalElements(),
                attributeResponseDTOs,
                requestPageableVO
        );

        return new ResponseEntity<>(responsePageableVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<AttributeResponseDTO>> getAttributeByID(Long attributeID) {
        Attribute attribute = this.checkIfAttributeExistByAttributeID(attributeID);
        ResponseVO<AttributeResponseDTO> responseVO = this.successResponseMaker(this.attributeMapper.toDto(attribute));
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    private <T> ResponseVO<T> successResponseMaker(T t) {
        return new ResponseVOBuilder<T>().addData(t).build();
    }

    private Attribute checkIfAttributeExistByAttributeID(Long attributeID) {
        return this.attributeRepository.findById(attributeID).orElseThrow(() -> new EntityNotFoundException("Il n'existe pas un attribut avec l'ID " + attributeID));
    }
}
