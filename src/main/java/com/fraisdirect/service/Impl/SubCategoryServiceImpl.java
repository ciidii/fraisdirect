package com.fraisdirect.service.Impl;
import com.fraisdirect.dto.DTOForAttributeSubCategory;
import com.fraisdirect.dto.SubCategoryRequestDTO;
import com.fraisdirect.dto.SubCategoryResponseDTO;
import com.fraisdirect.entity.*;
import com.fraisdirect.mapper.SubCategoryMapper;
import com.fraisdirect.repository.AttributeRepository;
import com.fraisdirect.repository.CategoryRepository;
import com.fraisdirect.repository.SubCategoryAttributeRepository;
import com.fraisdirect.repository.SubCategoryRepository;
import com.fraisdirect.service.SubCategoryService;
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
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final AttributeRepository attributeRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryMapper subCategoryMapper;
    private final SubCategoryAttributeRepository subCategoryAttributeRepository;
    @Override
    @Transactional
    public ResponseEntity<ResponseVO<SubCategoryResponseDTO>> addSubCategory(SubCategoryRequestDTO subCategoryRequestDTO) {

        //verify if the attributes,category all ready exist or else throw an entity not found exception.
        Category category = this.checkIfCategoryExistByID(subCategoryRequestDTO.getCategory().getCategoryID());
        List<Attribute> attributes = new ArrayList<>();
        subCategoryRequestDTO.getAttributes().forEach((attributeResponseDTO -> {
            attributes.add(this.checkIfAttributeExistByID(attributeResponseDTO.getAttributeID()));
        }));

        SubCategory subCategory = this.subCategoryMapper.toEntity(subCategoryRequestDTO);
        subCategory.setCategory(category);

        //objets for the intermediary table sub_category_attribute

        SubCategoryAttributeKey subCategoryAttributeKey = new SubCategoryAttributeKey();
        SubCategoryAttributes subCategoryAttributes = new SubCategoryAttributes();

        //save subcategory and get the object for saving in sub_category_attribute table
        subCategory = this.subCategoryRepository.save(subCategory);

        // save all attributes for subcategory
        for (Attribute attr : attributes){
            subCategoryAttributeKey.setAttributeID(attr.getAttributeID());
            subCategoryAttributeKey.setCategoryID(subCategory.getSubCategoryID());
            subCategoryAttributes.setSubCategoryAttributeKey(subCategoryAttributeKey);
            this.subCategoryAttributeRepository.save(subCategoryAttributes);
        }
        SubCategoryResponseDTO subCategoryResponseDTO = this.subCategoryMapper.toDto(subCategory);
        return new ResponseEntity<>(this.successResponseMaker(subCategoryResponseDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<SubCategoryResponseDTO>> modifySubCategory(SubCategoryResponseDTO categoryResponseDTO) {
        checkIfSubCategoryExistByID(categoryResponseDTO.getSubCategoryID());
        SubCategory subCategory = this.subCategoryMapper.toEntity(categoryResponseDTO);
        subCategory = this.subCategoryRepository.save(subCategory);
        SubCategoryResponseDTO dto = this.subCategoryMapper.toDto(subCategory);
        return new ResponseEntity<>(this.successResponseMaker(dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePageableVO<SubCategoryResponseDTO>> browserSubCategory(RequestPageableVO requestPageableVO) {
        PageRequest pageRequest = PageRequest.of(requestPageableVO.getPage() - 1, requestPageableVO.getRpp());
        Page<SubCategory> subCategoryPage = this.subCategoryRepository.findAll(pageRequest);

        List<SubCategoryResponseDTO> subCategoryResponseDtos = new ArrayList<>();

        for (SubCategory subCategory : subCategoryPage) {
            List<Attribute> attributes = this.subCategoryAttributeRepository.findAllBySubCategoryID(subCategory.getSubCategoryID());
            subCategoryResponseDtos.add(this.subCategoryMapper.toDto(subCategory,attributes));
        }

        ResponsePageableVO<SubCategoryResponseDTO> responsePageableVO = new ResponsePageableVO<>(
                subCategoryPage.getTotalElements(),
                subCategoryResponseDtos,
                requestPageableVO
        );

        return new ResponseEntity<>(responsePageableVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<SubCategoryResponseDTO>> getSubCategoryByID(Long categoryID) {
        SubCategory subCategory = this.checkIfSubCategoryExistByID(categoryID);
        ResponseVO<SubCategoryResponseDTO> responseVO = this.successResponseMaker(this.subCategoryMapper.toDto(subCategory));
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<SubCategoryResponseDTO>> addAttributeSubCategory(DTOForAttributeSubCategory dtoForAttributeSubCategory) {
       SubCategory subCategory= this.checkIfSubCategoryExistByID(dtoForAttributeSubCategory.getSubcategoryID());
        SubCategoryAttributeKey subCategoryAttributeKey = new SubCategoryAttributeKey();
        SubCategoryAttributes subCategoryAttributes = new SubCategoryAttributes();
        for (Long i: dtoForAttributeSubCategory.getLongArrays()){
            this.checkIfAttributeExistByID(i);
            subCategoryAttributeKey.setCategoryID(dtoForAttributeSubCategory.getSubcategoryID());
            subCategoryAttributeKey.setAttributeID(i);
            subCategoryAttributes.setSubCategoryAttributeKey(subCategoryAttributeKey);
            this.subCategoryAttributeRepository.save(subCategoryAttributes);
        }
        List<Attribute> attribute = this.subCategoryAttributeRepository.findAllBySubCategoryID(subCategory.getSubCategoryID());
        SubCategoryResponseDTO subCategoryResponseDTO = this.subCategoryMapper.toDto(subCategory,attribute);

        return new ResponseEntity<>(this.successResponseMaker(subCategoryResponseDTO),HttpStatus.OK);
    }


    private <T> ResponseVO<T> successResponseMaker(T t) {
        return new ResponseVOBuilder<T>().addData(t).build();
    }

    private SubCategory checkIfSubCategoryExistByID(Long categoryID) {
        return this.subCategoryRepository.findById(categoryID).orElseThrow(() -> new EntityNotFoundException("Il n'existe pas une sous-catégorie de produit d' " + categoryID));
    }
    private Attribute checkIfAttributeExistByID(Long attributeID){
        return this.attributeRepository.findById(attributeID).orElseThrow(()->new EntityNotFoundException("Il n'existe pas une d'attribut avec  une id" + attributeID));
    }

    private Category checkIfCategoryExistByID(Long attributeID){
        return this.categoryRepository.findById(attributeID).orElseThrow(()->new EntityNotFoundException("Il n'existe pas une catégorie de produit d'" + attributeID));
    }
}
