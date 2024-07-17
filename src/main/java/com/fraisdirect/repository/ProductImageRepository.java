package com.fraisdirect.repository;

import com.fraisdirect.entity.ProductImage;
import com.fraisdirect.entity.ProductImageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, ProductImageKey> {
    @Query("select pi.productImageKey from ProductImage pi where pi.productImageKey.productID= :productID")
    public List<ProductImageKey> findAllProductImageByProductID(@Param("productID") Long productID);
}