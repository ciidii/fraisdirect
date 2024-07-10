package com.fraisdirect.controller;

import com.fraisdirect.model.Client;
import com.fraisdirect.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    @Autowired
    public ClientService clientService;

    @GetMapping("/clients")
    public Iterable<Client> getClients(){
        return clientService.getClients();
    }
    @GetMapping("/client/{id}")
    public Client getClient(@PathVariable("id") String id){
        return clientService.getClientById(Integer.valueOf(id));
    }
    @PostMapping("/client")
    public Client createClient(@RequestBody Client client){
        return clientService.enregistreClient(client);
    }

    @PostMapping("/DesClients")
    public Iterable<Client> createDesClients(@RequestBody Iterable<Client> clients){
        return clientService.enrClients(clients);
    }
    @DeleteMapping("/supClient/{id}")
    public void supClient(@PathVariable("id") final int id){
         clientService.supClient(id);
    }
    @PutMapping("/client/{id}")
    public Client updateClient(@PathVariable("id") final int id, @RequestBody Client client){
        Client clientCourant = clientService.getClientById(id);
        String nom = client.getNom();
        if(nom!=null){
            clientCourant.setNom(nom);
        }
        String prenom = client.getPrenom();
        if(prenom!=null){
            clientCourant.setPrenom(prenom);
        }
        String adresse = client.getAdresse();
        if(adresse!=null){
            clientCourant.setAdresse(adresse);
        }
        String mail = client.getMail();
        if(mail!=null){
            clientCourant.setAdresse(mail);
        }
        String tel = client.getTel();
        if(tel!=null){
            clientCourant.setTel(tel);
        }
        return clientCourant;
    }
}
