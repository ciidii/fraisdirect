package com.fraisdirect.controller;

import com.fraisdirect.entity.Commande;
import com.fraisdirect.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @PostMapping("creer")
    public Commande creerCommande(@RequestBody Commande commande) {
        return commandeService.creerCommande(commande);
    }

    @PostMapping("/valider/{commandeId}")
    public void validerCommande(@PathVariable Long commandeId) {
        commandeService.validerCommande(commandeId);
    }

    @GetMapping("/{commandeId}/facture")
    public ResponseEntity<InputStreamResource> downloadFacture(@PathVariable Long commandeId) {
        Commande commande = commandeService.validerCommande(commandeId);
        return commandeService.genererFacturePDF(commande);
    }
}
