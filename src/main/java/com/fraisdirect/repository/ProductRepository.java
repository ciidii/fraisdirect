package com.fraisdirect.repository;

import com.fraisdirect.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from product p where p.status= true ")
    Page<Product> findSalableProduct(Pageable pageable);

    @Query("select p from product p where p.status= false")
    Page<Product> findNotSalableProduct(Pageable pageable);
}
