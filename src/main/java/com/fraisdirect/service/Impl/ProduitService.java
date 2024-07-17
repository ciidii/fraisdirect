package com.fraisdirect.service.Impl;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class ProduitService {
     /*
    @Autowired
    ProduitRepository produitRepository;
    public Optional<ProduitM> getProdById(Integer id){
        return produitRepository.findById(id);
    }
  public Iterable<ProduitM> getProd(){
        return produitRepository.findAll();
    }


    public ProduitM enregistreProduit(ProduitM p){
        ProduitM prod = produitRepository.save(p);
        return prod;
    }
    public Iterable<ProduitM> enrProds(Iterable<ProduitM> produits){
        return produitRepository.saveAll(produits);
    }
    public void supProduit(ProduitM prod){
        produitRepository.delete(prod);
    }

    */
}
