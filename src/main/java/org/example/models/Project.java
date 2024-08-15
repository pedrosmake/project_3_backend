package org.example.models;

public class Project {
    private int id;
    private String name;
    private double value;
    private ProjectStatus status;

    private Client client;
    private Employee techlead;

    public Project(final int id,
                   final String name,
                   final double value,
                   final ProjectStatus status,
                   final Client client,
                   final Employee techlead) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.status = status;
        this.client = client;
        this.techlead = techlead;
    }

    public Project(final int id,
                   final String name,
                   final double value,
                   final ProjectStatus status) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.status = status;
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

    public Client getClient() {
        return client;
    }

    public void setClient(final Client client) {
        this.client = client;
    }

    public Employee getTechlead() {
        return techlead;
    }

    public void setTechlead(final Employee techlead) {
        this.techlead = techlead;
    }
}
