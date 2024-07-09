package com.fraisdirect.repository;

import com.fraisdirect.entity.QuantityBasedPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityBasedPriceRepository extends JpaRepository<QuantityBasedPrice,Long> {
}
