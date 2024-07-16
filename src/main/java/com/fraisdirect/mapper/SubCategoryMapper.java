package com.fraisdirect.mapper;

import com.fraisdirect.dto.SubCategoryRequestDTO;
import com.fraisdirect.dto.SubCategoryResponseDTO;
import com.fraisdirect.entity.Attribute;
import com.fraisdirect.entity.SubCategory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubCategoryMapper {

    private final CategoryMapper categoryMapper;
    private final AttributeMapper attributeMapper;

    public SubCategoryMapper(CategoryMapper categoryMapper, AttributeMapper attributeMapper) {
        this.categoryMapper = categoryMapper;
        this.attributeMapper = attributeMapper;
    }

    public SubCategoryResponseDTO toDto(SubCategory subCategory) {
        if (subCategory == null) {
            return null;
        }
        SubCategoryResponseDTO dto = new SubCategoryResponseDTO();
        dto.setSubCategoryID(subCategory.getSubCategoryID());
        dto.setNameSubCategory(subCategory.getNameSubCategory());
        dto.setDescriptionSubCategory(subCategory.getDescriptionSubCategory());
        dto.setCategory(this.categoryMapper.toDto(subCategory.getCategory()));
        //dto.setAttributes(this.attributeMapper.toDtoList(attributes));
        return dto;
    }

    public SubCategoryResponseDTO toDto(SubCategory subCategory, List<Attribute> attributes) {
        if (subCategory == null) {
            return null;
        }
        SubCategoryResponseDTO dto = new SubCategoryResponseDTO();
        dto.setSubCategoryID(subCategory.getSubCategoryID());
        dto.setNameSubCategory(subCategory.getNameSubCategory());
        dto.setDescriptionSubCategory(subCategory.getDescriptionSubCategory());
        dto.setCategory(this.categoryMapper.toDto(subCategory.getCategory()));
        dto.setAttributes(this.attributeMapper.toDtoList(attributes));
        return dto;
    }

    public SubCategory toEntity(SubCategoryRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        SubCategory subCategory = new SubCategory();
        subCategory.setNameSubCategory(dto.getNameSubCategory());
        subCategory.setDescriptionSubCategory(dto.getDescriptionSubCategory());
        subCategory.setCategory(this.categoryMapper.toEntity(dto.getCategory()));
        // subCategory.setAttributes(attributeMapper.toEntityList(dto.getAttributes()));
        return subCategory;
    }

    public SubCategory toEntity(SubCategoryResponseDTO dto) {
        if (dto == null) {
            return null;
        }
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryID(dto.getSubCategoryID());
        subCategory.setNameSubCategory(dto.getNameSubCategory());
        subCategory.setDescriptionSubCategory(dto.getDescriptionSubCategory());
        subCategory.setCategory(categoryMapper.toEntity(dto.getCategory()));
        // subCategory.setAttributes(attributeMapper.toEntityList(dto.getAttributes()));
        return subCategory;
    }

    public List<SubCategoryResponseDTO> toDtoList(List<SubCategory> subCategories) {
        if (subCategories == null) {
            return null;
        }
        return subCategories.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<SubCategory> toEntityList(List<SubCategoryRequestDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
