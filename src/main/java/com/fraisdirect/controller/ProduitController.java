package com.fraisdirect.controller;

import com.fraisdirect.model.Produit;
import com.fraisdirect.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProduitController {
    @Autowired
    private ProduitService produitService;
    @GetMapping("/produits")
    public Iterable<Produit> getProduits(){
        return produitService.getProd();
    }
    @GetMapping("/produit/{id}")
    public Produit getProduit(@PathVariable("id") final int id){
        Optional<Produit> produit = produitService.getProdById(id);
        return produit.get();
    }
    @PostMapping("/produit")
    public Produit createProduit(@RequestBody Produit produit){
        return produitService.enregistreProduit(produit);
    }
    @PostMapping("/produits")
    public Iterable<Produit> createProds(@RequestBody Iterable<Produit> produits){
        return produitService.enrProds(produits);
    }
    @DeleteMapping("/produit/{id}")
    public void supprimerProduit(@PathVariable("id") final int id){
        Produit p = produitService.getProdById(id).get();
        produitService.supProduit(p);
    }
}
