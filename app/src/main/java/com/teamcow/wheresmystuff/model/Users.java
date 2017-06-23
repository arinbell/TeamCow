package com.teamcow.wheresmystuff.model;

import java.util.ArrayList;



public class Users {

    private String username;
    private String password;
    private String userType;

    private static ArrayList<Users> userList = new ArrayList<Users>();

    public Users(String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        userList.add(this);
    }


   public static boolean check(String username){
       for (Users user : userList) {
           if(user.username.equals(username)) {
               return true;
           }
       }
       return false;
   }

   public static ArrayList<Users> getUsers() {
       return userList;
   }

   @Override
   public String toString() {
       return username + " " + password + " " + userType;
   }
}
