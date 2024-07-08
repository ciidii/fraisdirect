package com.fraisdirect.repository;

import com.fraisdirect.entity.Attribute;
import com.fraisdirect.entity.SubCategoryAttributeKey;
import com.fraisdirect.entity.SubCategoryAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryAttributeRepository extends JpaRepository<SubCategoryAttributes, SubCategoryAttributeKey> {
    @Query("select a.attribute from SubCategoryAttributes a where a.subCategoryAttributeKey.categoryID= :subCategoryID")
    List<Attribute> findAllBySubCategoryID(@Param("subCategoryID") Long subCategoryID);
}
