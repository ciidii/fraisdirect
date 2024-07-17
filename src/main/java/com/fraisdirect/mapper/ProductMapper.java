package com.fraisdirect.mapper;

import com.fraisdirect.dto.product.ProductRequestDTO;
import com.fraisdirect.dto.product.ProductResponseDTO;
import com.fraisdirect.entity.Attribute;
import com.fraisdirect.entity.Product;
import com.fraisdirect.entity.ProductImageKey;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductMapper {

    private final SubCategoryMapper subCategoryMapper;
    private final ProductImageMapper productImageMapper;

    public ProductResponseDTO toDto(Product product) {
        if (product == null) {
            return null;
        }
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductID(product.getProductID());
        dto.setName(product.getName());
        dto.setCodeProduct(product.getCodeProduct());
        dto.setDescription(product.getDescription());
        dto.setQuantity(product.getQuantity());
        dto.setStatus(product.isStatus());
        dto.setSubCategoryResponseDTO(subCategoryMapper.toDto(product.getSubCategory()));
        return dto;
    }

    public ProductResponseDTO toDto(Product product, List<ProductImageKey> productImages, List<Attribute> attributes) {
        if (product == null) {
            return null;
        }
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductID(product.getProductID());
        dto.setName(product.getName());
        dto.setCodeProduct(product.getCodeProduct());
        dto.setDescription(product.getDescription());
        dto.setQuantity(product.getQuantity());
        dto.setStatus(product.isStatus());
            productImages.forEach(productImageKey -> {
                dto.setImages(this.productImageMapper.toDtoResponse(productImageKey));
            });
        dto.setSubCategoryResponseDTO(subCategoryMapper.toDto(product.getSubCategory(),attributes));
        return dto;
    }

    public Product toEntity(ProductRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setCodeProduct(dto.getCodeProduct());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setStatus(dto.isStatus());
        product.setQuantity(dto.getQuantity());
        // product.getSubCategory().setSubCategoryID(dto.getSubCategory());
        return product;
    }

    public Product toEntity(ProductResponseDTO dto) {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setCodeProduct(dto.getCodeProduct());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setQuantity(dto.getQuantity());
        product.setStatus(dto.isStatus());
        product.setSubCategory(subCategoryMapper.toEntity(dto.getSubCategoryResponseDTO()));
        return product;
    }

    public List<ProductResponseDTO> toDtoList(List<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Product> toEntityList(List<ProductRequestDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
