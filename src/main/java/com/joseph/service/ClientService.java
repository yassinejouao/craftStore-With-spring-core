package com.joseph.service;

import java.util.List;

import com.joseph.entity.Client;
// import com.joseph..exception.ResourceNotFoundException;

public interface ClientService {

    public List<Client> getClients();

    public void saveClient(Client Client);

    public Client getClient(int idclient);

    public void deleteClient(int idclient);

}