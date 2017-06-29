package com.teamcow.wheresmystuff.model;

import java.util.ArrayList;

/**
 * Created by arinb on 6/29/2017.
 */

public class UserDatabase {
    private static ArrayList<User> userList = new ArrayList<User>();

    public boolean checkDuplicate(String userID) {
        for (User u : userList) {
            if (u.getUserID().equals(userID)) {
                return true;
            }
        }
        return false;
    }

    public boolean addUser(String userID, String password, UserType type) {
        for (User u : userList) {
            if (u.getUserID().equals(userID)) {
                return false;
            }
        }
        userList.add(new User(userID, password, type));
        return true;
    }

    public boolean addUser(User user) {
        for (User u : userList) {
            if (u.getUserID().equals(user.getUserID())) {
                return false;
            }
        }
        userList.add(user);
        return true;
    }

    public User matchUser(String id, String password) {
        for (User u : userList) {
            if (u.getUserID().equals(id)) {
                if (u.matchPassword(password)) {
                    return u;
                }
            }
        }
        return null;
    }
}
