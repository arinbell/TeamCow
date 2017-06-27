package com.teamcow.wheresmystuff.model;

/**
 * Created by WAnya on 6/22/2017.
 * This class holds data for the items registered on the app
 */

public class LostItem {
    //holds the name of item
    private String name;

    // holds the description of item
    private String description;

    // holds the user associated with a given item
    private User user;

    public LostItem(String name, String des) {
        this.name = name;
        this.description = des;
    }

    /**
     * Allows the name of an item to be retrieved
     * @return the name of an item
     */
    public String getName() {
        return name;
    }

    /**
     * Allows the description of an item to be retrieved
     * @return the description of an item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Allows the user associated with an item to be retrieved
     * @return the user associated with an item
     */
    public User getUser() {
        return user;
    }

}
