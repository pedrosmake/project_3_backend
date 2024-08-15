package org.example.daos;

import org.example.exceptions.Entity;
import org.example.exceptions.FailedToCreateException;
import org.example.models.EmployeeRequest;
import org.example.models.EmployeeResponse;
import org.example.models.SalesEmployeeRequest;
import org.example.models.SalesEmployeeResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    public static final Integer ID_3 = 3;
    public static final Integer ID_4 = 4;
    public int createEmployee(
            final EmployeeRequest employeeRequest) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO Employee"
                    + " (name,"
                    + " salary,"
                    + " bank_account_number,"
                    + "national_insurance_number) VALUES (?,?,?,?);";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query,
                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,
                    employeeRequest.getName());
            preparedStatement.setDouble(2,
                    employeeRequest.getSalary());
            preparedStatement.setString(ID_3,
                    employeeRequest.getBankAccount());
            preparedStatement.setString(ID_4,
                    employeeRequest.getInsuranceNumber());


            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return -1;
        }
    }

    public int createEmployee(
            final SalesEmployeeRequest employeeRequest)
            throws SQLException, FailedToCreateException {
        int id = createEmployee((EmployeeRequest) employeeRequest);

        if (id != -1) {
            try (Connection connection = DatabaseConnector.getConnection()) {
                String query =
                        "INSERT INTO Commission_Rate (id,value) VALUES (?,?);";
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query);

                preparedStatement.setInt(1,
                        id);
                preparedStatement.setDouble(2,
                        employeeRequest.getCommissionRate());

                preparedStatement.executeUpdate();
            }
        } else {
            throw new FailedToCreateException(Entity.EMPLOYEE);
        }
        return id;
    }

    public List<EmployeeResponse> getAllEmployees() throws SQLException {
        List<EmployeeResponse> employees = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM `Employee`;"
            );

            while (resultSet.next()) {
                EmployeeResponse employeeResponse = new EmployeeResponse(
                        resultSet.getString("name"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("bank_account_number"),
                        resultSet.getString(
                                "national_insurance_number")
                );

                employees.add(employeeResponse);
            }

        }
        return employees;
    }

    public List<SalesEmployeeResponse> getAllSalesEmployees()
            throws SQLException {
        List<SalesEmployeeResponse> employees = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM `Employee` "
                            + "JOIN `Commission_Rate` USING(id);"
            );

            while (resultSet.next()) {
                SalesEmployeeResponse employeeResponse =
                        new SalesEmployeeResponse(
                        resultSet.getString("name"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("bank_account_number"),
                        resultSet.getString(
                                "national_insurance_number"),
                        resultSet.getDouble("value")
                );

                employees.add(employeeResponse);
            }

        }
        return employees;
    }
}
