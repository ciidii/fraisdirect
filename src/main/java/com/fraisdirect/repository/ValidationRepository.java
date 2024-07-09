package com.fraisdirect.repository;

import com.fraisdirect.entity.Validation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation, Integer> {
  Optional<Validation> findByCode(String code);
}