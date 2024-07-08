package tech.arona.avis.repository;

import org.springframework.data.repository.CrudRepository;
import tech.arona.avis.entities.Utilisateur;

import java.util.Optional;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByEmail(String email);
}
