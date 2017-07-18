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

    // holds the x-coordinate
    private double x_coord;

    // holds the y-coordinate
    private double y_coord;

    // holds the item type
    private ItemType type;

    // holds whether the poster lost or found the item
    private PosterType poster;

    public LostItem(String name, String des, ItemType type, PosterType poster) {
        this.name = name;
        this.description = des;
        this.type = type;
        this.poster = poster;
        x_coord = 0;
        y_coord = 0;
    }

    public LostItem(String name, String des, ItemType type, PosterType poster, double x_coord,
                    double y_coord) {
        this.name = name;
        this.description = des;
        this.type = type;
        this.poster = poster;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
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

    /**
     * Allows the x_coord of an item to be retrieved
     * @return the x_coord of an item
     */
    public double getX_Coord() {
        return x_coord;
    }

    /**
     * Allows the y_coord of an item to be retrieved
     * @return the y_coord of an item
     */
    public double getY_Coord() {
        return y_coord;
    }
}
