package com.teamcow.wheresmystuff.model;

/**
 * Created by Tony on 7/10/2017.
 * This class holds enum for the types of items
 */

public enum ItemType {
    CLOTHES("Clothes"),
    TOILETRY("Toiletry"),
    JEWELRY("Jewelry"),
    ELECTRONIC("Electronic");

    private String itemType;

    ItemType(String itemType){
        this.itemType = itemType;
    }

    public String toString(){
        return itemType;
    }
}
