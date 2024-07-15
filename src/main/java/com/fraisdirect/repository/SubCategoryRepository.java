package com.fraisdirect.repository;

import com.fraisdirect.entity.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    @Query("select sc from SubCategory sc where sc.category.categoryID = :categoryID")
    Page<SubCategory> getSubCategoryByCategoryID(PageRequest pageRequest,@Param("categoryID") Long categoryID);
}
