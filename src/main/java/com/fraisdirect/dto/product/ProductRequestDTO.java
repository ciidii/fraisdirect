package com.fraisdirect.dto.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductRequestDTO {
    @NotNull
    @Size(min = 5,max = 5)
    private String codeProduct;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private float basicPrice;
    private boolean status;
    private int quantity;
    @NotNull
    private Long subCategory;
    List<MultipartFile> images;
}
