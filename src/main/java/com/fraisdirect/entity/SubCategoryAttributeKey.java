package com.fraisdirect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class SubCategoryAttributeKey implements Serializable {
    @Column(name = "attribute_id")
    private  Long attributeID;
    @Column(name = "category_id")
    private Long categoryID;
}
