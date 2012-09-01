package com.menuve.model;

import java.util.Date;

/**
 * User: igabba
 */
public class Comment {

    private Long id;
    private Long userId;
    private String description;
    private Long menu_id;
    private Date createdOn;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getUserId() {

        return userId;
    }

    public void setUserId(Long userId) {

        this.userId = userId;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Long getMenu_id() {

        return menu_id;
    }

    public void setMenu_id(Long menu_id) {

        this.menu_id = menu_id;
    }

    public Date getCreatedOn() {

        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {

        this.createdOn = createdOn;
    }
}
