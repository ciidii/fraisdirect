package com.fraisdirect.service.Impl;

import com.fraisdirect.dto.product.ProductRequestDTO;
import com.fraisdirect.dto.product.ProductResponseDTO;
import com.fraisdirect.entity.*;
import com.fraisdirect.mapper.ProductMapper;
import com.fraisdirect.repository.*;
import com.fraisdirect.service.CatalogueService;
import com.fraisdirect.service.FileStorageService;
import com.fraisdirect.utils.RequestPageableVO;
import com.fraisdirect.utils.ResponsePageableVO;
import com.fraisdirect.utils.ResponseVO;
import com.fraisdirect.utils.ResponseVOBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aspectj.apache.bcel.generic.LineNumberGen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CatalogueServiceImpl implements CatalogueService {
    private final SubCategoryRepository subCategoryRepository;
    private final ProductMapper productMapper;
    private final FileStorageService fileStorageService;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final SubCategoryAttributeRepository subCategoryAttributeRepository;

    @Override
    public ResponseEntity<ResponseVO<ProductResponseDTO>> addItem(ProductRequestDTO productRequestDTO) {
        SubCategory subCategory = this.checkIfSubcategoryExist(productRequestDTO.getSubCategory());
        Product product = this.productMapper.toEntity(productRequestDTO);
        product.setSubCategory(subCategory);
        product = productRepository.save(product);
        List<String> urls = this.fileStorageService.saveFile(productRequestDTO.getImages(), product.getProductID());
        ProductImageKey productImageKey = new ProductImageKey();
        ProductImage productImage = new ProductImage();
        boolean firstItm = true;
        for (String url : urls) {
            productImageKey.setImageUrl(url);
            productImageKey.setProductID(product.getProductID());
            if (firstItm) {
                productImageKey.setPrincipal(true);
                firstItm = false;
            }
            productImage.setProductImageKey(productImageKey);
            this.productImageRepository.save(productImage);
        }
        ProductResponseDTO dto = this.productMapper.toDto(product);
        ResponseVO<ProductResponseDTO> responseVO = new ResponseVOBuilder<ProductResponseDTO>().addData(dto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponsePageableVO<ProductResponseDTO>> browser(RequestPageableVO requestPageableVO, int status) {
        PageRequest pageRequest = PageRequest.of(requestPageableVO.getPage() - 1, requestPageableVO.getRpp());
        Page<Product> productPage;
        if (status == 0) {
            productPage = this.productRepository.findAll(pageRequest);
        } else if (status == 1) {
            productPage = this.productRepository.findSalableProduct(pageRequest);
        }
        else {
            productPage = this.productRepository.findNotSalableProduct(pageRequest);
        }

        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        List<ProductImageKey> productImages;
        for (Product product : productPage) {
            productImages = this.productImageRepository.findAllProductImageByProductID(product.getProductID());
            List<Attribute> attributes = this.getProductAttribute(product.getProductID());
            productResponseDTOS.add(this.productMapper.toDto(product, productImages,attributes));
        }
        ResponsePageableVO<ProductResponseDTO> responsePageableVO = new ResponsePageableVO<>(
                productPage.getTotalElements(),
                productResponseDTOS,
                requestPageableVO
        );
        return new ResponseEntity<>(responsePageableVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<ProductResponseDTO>> getProductById(Long productID) {
       Product product = checkIfProductExistBy(productID);
        List<Attribute> attributes = this.getProductAttribute(productID);
       ProductResponseDTO productResponseDTO = this.productMapper.toDto(product,this.productImageRepository.findAllProductImageByProductID(productID),attributes);

        ResponseVO<ProductResponseDTO> responseVO = new ResponseVOBuilder<ProductResponseDTO>().addData(productResponseDTO).build();
        return new ResponseEntity<>(responseVO,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePageableVO<ProductResponseDTO>> findProductsByDescription(String description) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> removeProduct(Long productID) {
        return null;
    }

    @Override
    public ResponseEntity<ResponsePageableVO<ProductResponseDTO>> browserProductBySubcategory(RequestPageableVO requestPageableVO, Long subcategoryID) {
        checkIfSubcategoryExist(subcategoryID);

        PageRequest pageRequest = PageRequest.of(requestPageableVO.getPage() - 1, requestPageableVO.getRpp());
        Page<Product> productPage;
        productPage = this.productRepository.browserProductBySubcategory(pageRequest,subcategoryID);

        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        List<ProductImageKey> productImages;
        for (Product product : productPage) {
            productImages = this.productImageRepository.findAllProductImageByProductID(product.getProductID());
            List<Attribute> attributes = this.getProductAttribute(product.getProductID());
            productResponseDTOS.add(this.productMapper.toDto(product, productImages,attributes));
        }
        ResponsePageableVO<ProductResponseDTO> responsePageableVO = new ResponsePageableVO<>(
                productPage.getTotalElements(),
                productResponseDTOS,
                requestPageableVO
        );
        return new ResponseEntity<>(responsePageableVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> deleteItem(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseVO<ProductResponseDTO>> updateItem(Long id, ProductRequestDTO productRequestDTO) {
        return null;
    }

    private SubCategory checkIfSubcategoryExist(Long subCategoryID) {
        return this.subCategoryRepository.findById(subCategoryID).orElseThrow(() -> new EntityNotFoundException("Le sous-catégory n'est pas"));
    }
    private Product checkIfProductExistBy(Long productID) {
        return this.productRepository.findById(productID).orElseThrow(() -> new EntityNotFoundException("Le sous-catégory n'est pas"));
    }
    private List<Attribute> getProductAttribute(Long productID){
        checkIfProductExistBy(productID);
        SubCategory productSubCategory = this.productRepository.findById(productID).orElseThrow().getSubCategory();
       return this.subCategoryAttributeRepository.findAllBySubCategoryID(productSubCategory.getSubCategoryID());
    }
}

