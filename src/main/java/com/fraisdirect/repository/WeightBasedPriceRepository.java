package com.fraisdirect.repository;

import com.fraisdirect.entity.WeightBasedPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightBasedPriceRepository extends JpaRepository<WeightBasedPrice,Long> {
}
