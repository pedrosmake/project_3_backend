package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeRequest {
    private String name;
    private double salary;
    private String bankAccount;
    private String insuranceNumber;

    @JsonCreator
    public EmployeeRequest(final @JsonProperty("name") String name,
                           final @JsonProperty("salary") double salary,
                           final @JsonProperty("bankAccount")
                               String bankAccount,
                           final @JsonProperty("insuranceNumber")
                               String insuranceNumber) {
        this.name = name;
        this.salary = salary;
        this.bankAccount = bankAccount;
        this.insuranceNumber = insuranceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(final double salary) {
        this.salary = salary;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(final String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(final String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }
}
