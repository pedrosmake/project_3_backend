package org.example.models;

import java.sql.Date;

public class Client {
    private int id;
    private String name;
    private String address;
    private String phone;
    private Date createDate;
    private int acquiredBy;

    public Client(final int id,
                  final String name,
                  final String address,
                  final String phone,
                  final Date createDate,
                  final int acquiredBy) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.createDate = createDate;
        this.acquiredBy = acquiredBy;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(final Date createDate) {
        this.createDate = createDate;
    }

    public int getAcquiredBy() {
        return acquiredBy;
    }

    public void setAcquiredBy(final int acquiredBy) {
        this.acquiredBy = acquiredBy;
    }
}
