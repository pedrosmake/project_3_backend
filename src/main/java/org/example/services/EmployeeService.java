package org.example.services;

import org.example.daos.EmployeeDao;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.EmployeeRequest;
import org.example.models.EmployeeResponse;
import org.example.models.SalesEmployeeRequest;
import org.example.validators.EmployeeValidator;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {

    EmployeeDao employeeDao;
    EmployeeValidator employeeValidator;
    public EmployeeService(final EmployeeDao employeeDao,
                           final EmployeeValidator employeeValidator) {
        this.employeeDao = employeeDao;
        this.employeeValidator = employeeValidator;
    }

    public int createEmployee(final EmployeeRequest employeeRequest)
            throws SQLException, InvalidException {
        employeeValidator.validateDeliveryEmployee(employeeRequest);
        return employeeDao.createEmployee(employeeRequest);

    }

    public int createEmployee(final SalesEmployeeRequest employeeRequest)
            throws SQLException, FailedToCreateException, InvalidException {
        employeeValidator.validateSalesEmployee(employeeRequest);
        return employeeDao.createEmployee(employeeRequest);
    }

    public List<EmployeeResponse> getAllEmployees() throws SQLException {
        return  employeeDao.getAllEmployees();
    }

    public Object getAllSalesEmployees() throws SQLException {
        return  employeeDao.getAllSalesEmployees();
    }
}
