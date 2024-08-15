package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class ClientRequest {
    private String name;
    private String address;
    private String phone;
    private Date createDate;
    private int acquiredBy;

    @JsonCreator
    public ClientRequest(@JsonProperty("name") final String name,
                         @JsonProperty("address") final String address,
                         @JsonProperty("phone") final String phone,
                         @JsonProperty("createDate") final Date createDate,
                         @JsonProperty("acquiredBy") final int acquiredBy) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.createDate = createDate;
        this.acquiredBy = acquiredBy;
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
