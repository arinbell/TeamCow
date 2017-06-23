package com.teamcow.wheresmystuff.model;

/**
 * Created by WAnya on 6/22/2017.
 */

public class LostItem {
    private String name;
    private String description;
    private User user;

    public LostItem(String name, String des) {
        this.name = name;
        this.description = des;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

}
