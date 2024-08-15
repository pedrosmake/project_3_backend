package org.example.daos;

import org.example.models.Client;
import org.example.models.TopClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, name, address, phone_number, "
                            + "create_date, acquired_by FROM Client;");

            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone_number"),
                        resultSet.getDate("create_date"),
                        resultSet.getInt("acquired_by")
                );

                clients.add(client);
            }
        }
        return clients;
    }

    public TopClient getTopClient() throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT c.Name as `Name`, SUM(p.value) AS `Value` \n"
                            + "FROM Project p\n"
                            + "JOIN Client c ON p.client_id=c.id\n"
                            + "GROUP BY c.Name\n"
                            + "ORDER BY sum(p.value)\n"
                            + "DESC LIMIT 1;"
            );

            while (resultSet.next()) {
                return new TopClient(
                        resultSet.getString("Name"),
                        resultSet.getDouble("Value")
                );
            }
        }
        return null;


    }
}
