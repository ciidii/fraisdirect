package com.fraisdirect.service.Impl;
import com.fraisdirect.entity.Validation;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {

    JavaMailSender javaMailSender;

    public void envoyer( Validation validation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("arona10ndiaye@gmail.com");
        message.setTo(validation.getUtilisateur().getEmail());
        message.setSubject("Votre code d'activation");

        String texte = String.format(
                "Bonjour %s, Votre code d'action est %s; A bientôt",
                validation.getUtilisateur().getNom(),
                validation.getCode());
        message.setText(texte);
        javaMailSender.send(message);
    }
}
