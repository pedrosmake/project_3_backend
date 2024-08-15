package org.example.services;

import org.example.daos.ClientDao;
import org.example.mappers.ClientMapper;
import org.example.models.ClientResponse;
import org.example.models.TopClient;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    ClientDao clientDao;

    public ClientService(final ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<ClientResponse> getAllClients() throws SQLException {
        return ClientMapper
                .mapClientListToClientResponseList(
                        clientDao.getAllClients()
                );
    }

    public TopClient getTopClient() throws SQLException {
        TopClient client = clientDao.getTopClient();
        if (client == null) {
            throw new SQLException("No client found");
        }
        return client;
    }
}
