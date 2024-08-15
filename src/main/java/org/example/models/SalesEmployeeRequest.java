package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesEmployeeRequest extends EmployeeRequest {
    private double commissionRate;

    @JsonCreator
    public SalesEmployeeRequest(final @JsonProperty("name") String name,
                                final @JsonProperty("salary") double salary,
                                final @JsonProperty("bankAccount")
                                    String bankAccount,
                                final @JsonProperty("insuranceNumber")
                                    String insuranceNumber,
                                final @JsonProperty("commissionRate")
                                    double commissionRate) {
        super(name, salary, bankAccount, insuranceNumber);
        this.commissionRate = commissionRate;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(final double commissionRate) {
        this.commissionRate = commissionRate;
    }
}
