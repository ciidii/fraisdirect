package com.fraisdirect.service.Impl;

import com.fraisdirect.model.Client;
import com.fraisdirect.repository.ClientRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    public Client getClientById(Integer id){
        Optional<Client> client = clientRepository.findById(id);

        return client.get();
    }
    public Iterable<Client> getClients(){
        return clientRepository.findAll();
    }
    public Client enregistreClient(Client client){
      return clientRepository.save(client);
    }
    public Iterable<Client> enrClients(Iterable<Client> clients){
        return clientRepository.saveAll(clients);
    }
    public void supClient(int id){
        Client client = getClientById(id);
        clientRepository.delete(client);
    }
}
