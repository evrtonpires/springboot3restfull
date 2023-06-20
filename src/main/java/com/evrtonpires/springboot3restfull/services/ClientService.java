package com.evrtonpires.springboot3restfull.services;

import com.evrtonpires.springboot3restfull.domain.Client;
import com.evrtonpires.springboot3restfull.repositories.ClientRepository;
import com.evrtonpires.springboot3restfull.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getById(UUID id) {
        Optional<Client> clientO = clientRepository.findById(id);
        return clientO.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: "+ Client.class.getName()));
    }

    public Client save(Client client) {

        return clientRepository.save(client);
    }

    public List<Client> saveAll(List<Client> categories) {

        return clientRepository.saveAll(categories);
    }


    public Object update(UUID id, Client client) {
        Optional<Client> clientO = clientRepository.findById(id);
        if (clientO.isEmpty()) {
            return "Product not found.";
        }


        return clientRepository.save(client);
    }


    public void delete(UUID id) {
        Optional<Client> clientO = clientRepository.findById(id);
        clientO.<Object>map(
                client -> {
                    clientRepository.delete(clientO.get());
                    return "Product deleted successfully.";
                }).orElseGet(() -> "Product not found.");
    }

}
