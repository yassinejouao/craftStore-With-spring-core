package com.joseph.service.Impl;

import java.util.List;

import com.joseph.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joseph.repository.ClientRepository;
import com.joseph.entity.Client;
// import com.joseph.exception.ResourceNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public void saveClient(Client theCustomer) {
        clientRepository.save(theCustomer);
    }

    @Override
    @Transactional
    public Client getClient(int id) throws NullPointerException {
        return clientRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Client with ID = ${id} is not found"));
    }

    @Override
    @Transactional
    public void deleteClient(int theId) {
        clientRepository.deleteById(theId);
    }
}