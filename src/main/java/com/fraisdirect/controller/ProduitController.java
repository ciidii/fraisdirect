package com.fraisdirect.controller;
import com.fraisdirect.entity.Produit;
import com.fraisdirect.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produits")
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("/{produitId}")
    public Produit getProduitById(@PathVariable Long produitId) {
        return produitRepository.findById(produitId).orElse(null);
    }

    @PostMapping("/ajouter")
    public Produit ajouterProduit(@RequestBody Produit produit) {
        return produitRepository.save(produit);
    }

    @PutMapping("/{produitId}")
    public Produit modifierProduit(@PathVariable Long produitId, @RequestBody Produit produitModifie) {
        Produit produit = produitRepository.findById(produitId).orElse(null);
        if (produit != null) {
            produit.setNom(produitModifie.getNom());
            produit.setPrix(produitModifie.getPrix());
            return produitRepository.save(produit);
        }
        return null;
    }

    @DeleteMapping("/{produitId}")
    public void supprimerProduit(@PathVariable Long produitId) {
        produitRepository.deleteById(produitId);
    }

    @GetMapping("/liste")
    public List<Produit> listeProduits() {
        return produitRepository.findAll();
    }
}

