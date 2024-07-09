package com.fraisdirect.repository;

import com.fraisdirect.entity.ProductPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPriceModelRepository extends JpaRepository<ProductPriceModel,Long> {
    @Query("select ppm from ProductPriceModel ppm where ppm.product.productID =:productID and ppm.status = true")
    Optional<ProductPriceModel> findActivePrice(@Param("productID") Long productID);
}
