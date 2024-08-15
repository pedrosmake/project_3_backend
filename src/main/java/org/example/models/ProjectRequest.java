package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectRequest {
    private String name;
    private double value;
    private ProjectStatus status;

    // do zmiany na obiekty
    private int clientId;
    private int techleadId;

    @JsonCreator
    public ProjectRequest(@JsonProperty("name") final String name,
                          @JsonProperty("value") final double value,
                          @JsonProperty("status") final ProjectStatus status,
                          @JsonProperty("clientId") final int clientId,
                          @JsonProperty("techleadId") final int techleadId) {
        this.name = name;
        this.value = value;
        this.status = status;
        this.clientId = clientId;
        this.techleadId = techleadId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(final double value) {
        this.value = value;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(final ProjectStatus status) {
        this.status = status;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(final int clientId) {
        this.clientId = clientId;
    }

    public int getTechleadId() {
        return techleadId;
    }

    public void setTechleadId(final int techleadId) {
        this.techleadId = techleadId;
    }
}
