package com.fraisdirect.mapper;

import com.fraisdirect.dto.CategoryResponseDTO;
import com.fraisdirect.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryResponseDTO toDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setCategoryID(category.getCategoryID());
        dto.setNameCategory(category.getNameCategory());
        dto.setDescription(category.getDescription());
        return dto;
    }

    public Category toEntity(CategoryResponseDTO dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryID(dto.getCategoryID());
        category.setNameCategory(dto.getNameCategory());
        category.setDescription(dto.getDescription());
        return category;
    }

    public List<CategoryResponseDTO> toDtoList(List<Category> categories) {
        if (categories == null) {
            return null;
        }
        return categories.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Category> toEntityList(List<CategoryResponseDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
