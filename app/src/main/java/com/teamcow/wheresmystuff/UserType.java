package com.teamcow.wheresmystuff;

/**
 * Created by arinb on 6/22/2017.
 */

public enum UserType {
    ANONYMOUS("Visitor"),
    USER("User"),
    MOD("Moderator"),
    ADMIN("Administrator");

    private String userType;

    UserType(String userType){
        this.userType = userType;
    }

    public String toString(){
        return userType;
    }
}
