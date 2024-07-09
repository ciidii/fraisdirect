package com.fraisdirect.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduit;
    private String libelle;
    private String description;
    private String Categorie;
    private String image;
    private double prix_Kg;
    private boolean promotion;
    private double reduction;

    public double getPrix_Kg(){
        if(promotion){
             return prix_Kg * (1-reduction/100);
        }
        else {
            return prix_Kg;
        }
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    public void appliquerReduction(){
        if(promotion){
            prix_Kg = prix_Kg * (1-reduction/100);
        }

    }
}
