package com.teamcow.wheresmystuff.model;

/**
 * Created by Tony on 7/10/2017.
 * This class holds enum for the type of poster
 */

public enum PosterType {
    FINDER("Finder"),
    LOSER("Loser");

    private String posterType;

    PosterType(String posterType){
        this.posterType = posterType;
    }

    public static boolean contains(String test) {
        for (PosterType i : PosterType.values()) {
            if (i.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return posterType;
    }
}
