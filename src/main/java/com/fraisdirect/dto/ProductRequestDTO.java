package com.fraisdirect.dto;

import com.fraisdirect.entity.SubCategory;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequestDTO {
    @NotNull(message = "le code du produit ne doit pas être null")
    @NotEmpty(message = "le code du produit ne doit pas être null")
    @Size(min = 5,max = 5, message = "le code produit doit avoir un taille de 5")
    private String codeProduct;
    private String description;
    @NotNull(message = "le prix de base du produit ne doit pas être null")
    @NotEmpty(message = "le prix de base du produit ne doit pas être null")
    private float basicPrice;
    private boolean status;
    @NotNull(message = "le sous sous-catégorie du produit ne peut pas être null")
    @NotEmpty(message = "le sous sous-catégorie du produit ne peut pas être vide")
    private SubCategory subCategory;
}
