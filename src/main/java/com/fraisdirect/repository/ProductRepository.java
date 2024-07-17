package com.fraisdirect.repository;

import com.fraisdirect.entity.Attribute;
import com.fraisdirect.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from product p where p.status= true ")
    Page<Product> findSalableProduct(Pageable pageable);

    @Query("select p from product p where p.status= false")
    Page<Product> findNotSalableProduct(Pageable pageable);

    @Query("select p from product p where p.subCategory.subCategoryID= :subcategoryID and p.status =true")
    Page<Product> browserProductBySubcategory(PageRequest pageRequest,@Param("subcategoryID") Long subcategoryID);

}
