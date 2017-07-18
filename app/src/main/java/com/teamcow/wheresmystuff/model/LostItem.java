package com.teamcow.wheresmystuff.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WAnya on 6/22/2017.
 * Edited by Tian Ze Qi
 * This class holds data for the items registered on the app
 */

public class LostItem {
    //holds the legal item types
    public static List<String> legalType = Arrays.asList("Clothes", "Toiletry", "Jewelry",
            "Electronic", "Other");

    //holds the name of item
    private String name;

    // holds the description of item
    private String description;

    // holds the user associated with a given item
    private User user;

    public LostItem(String name, String des, ItemType type, PosterType poster) {
        this.name = name;
        this.description = des;
        ItemType type1 = type;
        PosterType poster1 = poster;
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
