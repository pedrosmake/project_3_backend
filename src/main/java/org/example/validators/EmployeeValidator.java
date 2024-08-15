package org.example.validators;

import org.example.exceptions.Entity;
import org.example.exceptions.InvalidException;
import org.example.models.EmployeeRequest;
import org.example.models.SalesEmployeeRequest;

public class EmployeeValidator {

    public static final int MAX_NAME_LENGTH = 50;
    public static final double MAX_SALARY = 9999.99;
    public static final double MAX_ACCOUNT_NUMBER_LENGTH = 28;
    public static final double MAX_NATIONAL_INSURANCE_NUMBER_LENGTH = 30;
    public static final double MAX_COMMISSION_RATE = 100.0;


    public void validateDeliveryEmployee(final EmployeeRequest employeeRequest)
            throws InvalidException {
        if (employeeRequest.getName().length() > MAX_NAME_LENGTH) {
            throw new InvalidException(Entity.EMPLOYEE,
                    "name at max 50 chars");
        }
        if (employeeRequest.getSalary() > MAX_SALARY) {
            throw new InvalidException(Entity.EMPLOYEE,
                    "salary must be less than 10000");
        }
        if (employeeRequest.
                getBankAccount().
                length() > MAX_ACCOUNT_NUMBER_LENGTH) {
            throw new InvalidException(Entity.EMPLOYEE,
                    "bank acc length max 28");
        }
        if (employeeRequest.
                getInsuranceNumber().
                length() > MAX_NATIONAL_INSURANCE_NUMBER_LENGTH) {
            throw new InvalidException(Entity.EMPLOYEE,
                    "insurance length max 30");
        }
    }

    public void validateSalesEmployee(
            final SalesEmployeeRequest salesEmployeeRequest)
            throws InvalidException {
        validateDeliveryEmployee(salesEmployeeRequest);
        if (salesEmployeeRequest.getCommissionRate() > MAX_COMMISSION_RATE) {
            throw new InvalidException(Entity.EMPLOYEE,
                    "Commission rate cannot be grater than 100");
        }
    }
}
