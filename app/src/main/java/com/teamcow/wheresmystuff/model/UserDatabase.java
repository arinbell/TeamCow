package com.teamcow.wheresmystuff.model;

import java.util.ArrayList;

public class UserDatabase {
    private static ArrayList<LocalUser> userList = new ArrayList<>();

//    public boolean checkDuplicate(String userID) {
//        for (User u : userList) {
//            if (u.getUserID().equals(userID)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
    public boolean addUser(String userID, String password, UserType type) {
//        for (LocalUser u : userList) {
//            if (u.getUserID().equals(userID)) {
//                return false;
//            }
//        }
        //userList.add(new LocalUser(userID, password, type));
        return true;
    }
//
//    public static boolean addUser(User user) {
//        for (User u : userList) {
//            if (u.getUserID().equals(user.getUserID())) {
//                return false;
//            }
//        }
//        userList.add(user);
//        return true;
//    }
//
//    public User matchUser(String id, String password) {
//        for (User u : userList) {
//            if (u.getUserID().equals(id)) {
//                if (u.matchPassword(password)) {
//                    return u;
//                }
//            }
//        }
//        return null;
//    }


    /**
     * Allows the list of users to be retrieved
     * @return the list of users
     */
    public ArrayList<LocalUser> getUserList() {
        return userList;
    }
}
