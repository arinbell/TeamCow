package com.teamcow.wheresmystuff.model;

/**
 * Created by WAnya on 6/21/2017.
 *
 * This class will allow the user to store information about the items they post in our system
 */

public class LostItem {
    private String name;
    private String description;

    public LostItem (String n, String d) {
        this.name = n;
        this.description = d;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

