package com.fraisdirect.repository;

import com.fraisdirect.entity.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByEmail(String email);
}
