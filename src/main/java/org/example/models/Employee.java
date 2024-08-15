package org.example.models;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private String bankAccount;
    private String insuranceNumber;

    public Employee() {
    }

    public Employee(final int id,
                    final String name,
                    final double salary,
                    final String bankAccount,
                    final String insuranceNumber) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.bankAccount = bankAccount;
        this.insuranceNumber = insuranceNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
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
