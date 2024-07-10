package com.fraisdirect.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCommande;
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    private int idClient;
    @JoinColumn(name = "idProduit", referencedColumnName = "idProduit")
    private int idProduit;
    private int qty;
    private double prixTotal;
    private Date dateCommande;

    @PrePersist
    protected void onCreate() {
        this.dateCommande = new Date();
    }

}
