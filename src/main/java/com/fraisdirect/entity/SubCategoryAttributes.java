package com.fraisdirect.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class SubCategoryAttributes {
    @EmbeddedId
    private SubCategoryAttributeKey subCategoryAttributeKey;

    @ManyToOne()
    @JoinColumn(name = "attribute_id",insertable = false,updatable = false)
    private Attribute attribute;

    @ManyToOne()
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private SubCategory subCategory;
}
