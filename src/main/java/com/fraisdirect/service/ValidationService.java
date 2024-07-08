package tech.arona.avis.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.arona.avis.entities.Utilisateur;
import tech.arona.avis.entities.Validation;
import tech.arona.avis.repository.ValidationRepository;

import java.time.Instant;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@AllArgsConstructor
@Service

public class ValidationService {
    private ValidationRepository validationRepository;
    private NotificationService notificationService;
    public void enrigistrer(Utilisateur utilisateur) {
        Validation validation = new Validation();
        validation.setUtilisateur(utilisateur);
        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(10,MINUTES);
        validation.setExpire(expiration);
        Random random = new Random();
        int randomInt = random.nextInt(999999);
        String code=String.format("%06d", randomInt);
        validation.setCode(code);
        this.validationRepository.save(validation);
        this.notificationService.envoyer(validation);
        }
        public Validation LireEnFonctionDuCode(String code) {
        return this.validationRepository.findByCode(code).orElseThrow(()->new RuntimeException("Votre code est invalide"));

        }
}
