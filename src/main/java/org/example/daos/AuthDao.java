package org.example.daos;

import org.example.models.LoginRequest;
import org.example.models.RegisterRequest;
import org.example.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthDao {

    public static final int THREE = 3;
    public User getUser(final LoginRequest loginRequest) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT Username, Password, RoleID FROM `User`"
                    + " WHERE Username = ? and Password = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, loginRequest.getUsername());
            statement.setString(2, loginRequest.getPassword());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new User(
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getInt("RoleID")
                );
            }
        }
        return null;
    }

    public void register(final RegisterRequest registerRequest)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query =
                    "INSERT INTO `User` (Username, Password, RoleId)"
                            + " VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, registerRequest.getUsername());
            statement.setString(2, registerRequest.getPassword());
            statement.setInt(THREE, 1);

            statement.executeUpdate();
        }
    }
}
