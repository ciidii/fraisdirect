package com.fraisdirect.service;
import com.fraisdirect.entity.Commande;
import com.fraisdirect.entity.CommandeProduit;
import com.fraisdirect.entity.Produit;
import com.fraisdirect.repository.CommandeRepository;
import com.fraisdirect.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void validerCommande(Long commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        Map<String, Integer> produitsIndisponibles = new HashMap<>();

        for (CommandeProduit cp : commande.getProduits()) {
            Produit produit = cp.getProduit();
            int quantiteDemandee = cp.getQuantite();
            int quantiteEnStock = produit.getQuantiteEnStock();

            if (quantiteEnStock < quantiteDemandee) {
                produitsIndisponibles.put(produit.getNom(), quantiteDemandee - quantiteEnStock);
            }
        }

        if (!produitsIndisponibles.isEmpty()) {
            StringBuilder emailText = new StringBuilder("Bonjour,\n\nLes produits suivants ne sont pas disponibles en quantité suffisante :\n");
            produitsIndisponibles.forEach((nom, quantite) -> emailText.append(nom).append(": ").append(quantite).append(" manquant(s)\n"));
            emailText.append("\nCordialement,\nVotre Boutique");
            emailService.sendEmail(commande.getClientEmail(), "Disponibilité de votre commande", emailText.toString());
            // Mettre à jour le statut de la commande si nécessaire (optionnel)
            commande.setStatut(0); // Commande non validée en raison de produits indisponibles
            commandeRepository.save(commande);
        } else {
            // Tous les produits sont disponibles, donc on valide la commande
            // Mettre à jour le statut de la commande si nécessaire (optionnel)
            commande.setStatut(1); // Commande validée
            commandeRepository.save(commande);
        }
    }

    @Transactional
    public Commande creerCommande(Commande commande) {
        // Assurez-vous que chaque commandeProduit a une référence correcte à la commande parente
        for (CommandeProduit commandeProduit : commande.getProduits()) {
            commandeProduit.setCommande(commande);
        }

        // Sauvegardez la commande avec ses produits associés
        return commandeRepository.save(commande);
    }
}
