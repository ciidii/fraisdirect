package com.fraisdirect.controller;

import com.fraisdirect.model.Client;
import com.fraisdirect.model.Commande;
import com.fraisdirect.model.Produit;
import com.fraisdirect.services.ClientService;
import com.fraisdirect.services.CommandeService;
import com.fraisdirect.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandeController {
    @Autowired
    public CommandeService commandeService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProduitService produitService;

    @GetMapping("/commande")
    public Commande getCommande(@PathVariable Integer id){
        return commandeService.getCommande(id).get();
    }
    @GetMapping("/commandes")
    public Iterable<Commande> getCommndes(){
        return commandeService.getCommandes();
    }
    @PostMapping("/{id}/commanderProduit")
    public Commande commanderProduit(@PathVariable("id") Integer id, @RequestBody Commande commande){

            Produit produit = produitService.getProdById(commande.getIdProduit()).get();
            if (produit == null) {
                throw new RuntimeException("Produit invalide");
            }
            commande.setIdClient(id);
            double prix = produit.getPrix_Kg();
            commande.setPrixTotal(prix * commande.getQty());
            return commandeService.enrCommande(commande);

    }
    @DeleteMapping("/commande/{id}")
    public void supCommande(@PathVariable("id") Integer id){
        Commande commande = commandeService.getCommande(id).get();
        commandeService.supCommande(commande);
    }
}
