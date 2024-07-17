package com.fraisdirect.service.Impl;

import com.fraisdirect.dto.CategoryRequestDTO;
import com.fraisdirect.dto.CategoryResponseDTO;
import com.fraisdirect.entity.Category;
import com.fraisdirect.mapper.CategoryMapper;
import com.fraisdirect.repository.CategoryRepository;
import com.fraisdirect.service.CategoryService;
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
import org.springframework.web.servlet.function.EntityResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public ResponseEntity<ResponseVO<CategoryResponseDTO>> addCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = this.categoryMapper.toEntity(categoryRequestDTO);
        category = this.categoryRepository.save(category);
        CategoryResponseDTO categoryResponseDTO = this.categoryMapper.toDto(category);
        return new ResponseEntity<>(this.successResponseMaker(categoryResponseDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<CategoryResponseDTO>> modifyCategory(CategoryResponseDTO categoryResponseDTO) {
        checkIfCategoryExistByCategoryID(categoryResponseDTO.getCategoryID());
        Category category = this.categoryMapper.toEntity(categoryResponseDTO);
        category = this.categoryRepository.save(category);
        CategoryResponseDTO dto = this.categoryMapper.toDto(category);
        return new ResponseEntity<>(this.successResponseMaker(dto),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<CategoryResponseDTO>>> browserCategory() {
        List<Category> categoryPage = this.categoryRepository.findAll();

        List<CategoryResponseDTO> memberResponseDtos = new ArrayList<>();

        for (Category category : categoryPage) {
            memberResponseDtos.add(this.categoryMapper.toDto(category));
        }
        ResponseVO<List<CategoryResponseDTO>> responsePageableVO = new ResponseVOBuilder<List<CategoryResponseDTO>>().addData(memberResponseDtos).build();
        return new ResponseEntity<>(responsePageableVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<CategoryResponseDTO>> getCategoryByID(Long categoryID) {
        Category category = this.checkIfCategoryExistByCategoryID(categoryID);
        ResponseVO<CategoryResponseDTO> responseVO = this.successResponseMaker(this.categoryMapper.toDto(category));
        return new ResponseEntity<>(responseVO,HttpStatus.OK);
    }
    private <T> ResponseVO<T> successResponseMaker(T t){
        return new ResponseVOBuilder<T>().addData(t).build();
    }
    private Category checkIfCategoryExistByCategoryID(Long categoryID){
       return this.categoryRepository.findById(categoryID).orElseThrow(()->new EntityNotFoundException("Il n'existe pas un catégorie de produit d' "+categoryID));
    }
}
