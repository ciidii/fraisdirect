package com.fraisdirect.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class SubCategory {
    @Id
    @GeneratedValue
    private Long subCategoryID;
    private String nameSubCategory;
    private String descriptionSubCategory;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Attribute> attributes;
}
