package com.menuve.model;

import java.util.Date;

/**
 * User: igabba
 */
public class Menu {

    private Long id;
    private Long idResto;
    private String description;
    private String name;
    private Date createdOn;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getIdResto() {

        return idResto;
    }

    public void setIdResto(Long idResto) {

        this.idResto = idResto;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Date getCreatedOn() {

        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {

        this.createdOn = createdOn;
    }
}
