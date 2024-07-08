package tech.arona.avis.repository;

import org.springframework.data.repository.CrudRepository;
import tech.arona.avis.entities.Validation;

import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation, Integer> {
  Optional<Validation> findByCode(String code);
}
