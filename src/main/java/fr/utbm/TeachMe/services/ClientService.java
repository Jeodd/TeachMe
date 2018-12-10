package fr.utbm.TeachMe.services;

import java.io.Serializable;
import java.util.List;

import fr.utbm.TeachMe.entity.Client;
import fr.utbm.TeachMe.repository.ClientDao;

public class ClientService implements Serializable {

    // PROPERTIES
    ClientDao clientDao = new ClientDao();

    // CONSTRUCTORS
    public ClientService() {

    }

    // GETTERS
    public Client getClient(Client c) {
        return clientDao.getClient(c);
    }

    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }

    // SETTERS
    // FUNCTIONS AND METHODS
    public void saveClient(Client c) {
        clientDao.saveClient(c);
    }

    public void updateClient(Client c) {
        clientDao.updateClient(c);
    }

    public void deleteClient(Client c) {
        clientDao.deleteClient(c);
    }

}
