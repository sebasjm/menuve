package com.menuve.model;

import java.util.Date;

/**
 * User: igabba
 */
public class Resto {

    private Long id;
    private String name;
    private String address;
    private Date createdOn;
    private Date loggedOn;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public Date getCreatedOn() {

        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {

        this.createdOn = createdOn;
    }

    public Date getLoggedOn() {

        return loggedOn;
    }

    public void setLoggedOn(Date loggedOn) {

        this.loggedOn = loggedOn;
    }
}
