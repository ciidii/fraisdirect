package com.fraisdirect.service;

import com.fraisdirect.model.Produit;
import com.fraisdirect.repository.ProduitRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ProduitService {

    @Autowired
    ProduitRepository produitRepository;
    public Optional<Produit> getProdById(Integer id){
        return produitRepository.findById(id);
    }
    public Iterable<Produit> getProd(){
        return produitRepository.findAll();
    }
    public Produit enregistreProduit(Produit p){
        Produit prod = produitRepository.save(p);
        return prod;
    }
    public Iterable<Produit> enrProds(Iterable<Produit> produits){
        return produitRepository.saveAll(produits);
    }
    public void supProduit(Produit prod){
        produitRepository.delete(prod);
    }
}
