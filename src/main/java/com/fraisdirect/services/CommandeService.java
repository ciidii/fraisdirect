package com.fraisdirect.services;

import com.fraisdirect.model.Commande;
import com.fraisdirect.repository.CommandeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class CommandeService {

    @Autowired
    CommandeRepository commandeRepository;

    public Optional<Commande> getCommande(Integer id){
        return commandeRepository.findById(id);
    }
    public Iterable<Commande> getCommandes(){
        return commandeRepository.findAll();
    }
    public Commande enrCommande(Commande commande){
        return commandeRepository.save(commande);
    }
    public Iterable<Commande> enrCommandes(Iterable<Commande> lists){
        return commandeRepository.saveAll(lists);
    }
    public void supCommande(Commande com){
        commandeRepository.delete(com);
    }
}
