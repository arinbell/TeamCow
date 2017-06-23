package com.teamcow.wheresmystuff.controller;

import android.content.Context;

import com.teamcow.wheresmystuff.model.Users;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileSave {
    public static void fileSave(Context c) throws IOException {
        ArrayList<Users> loadUsers = Users.getUsers();
        FileOutputStream output = c.openFileOutput("Users.txt", Context.MODE_PRIVATE);
        for (Users users : loadUsers) {
            String message = users.toString() + "\n";
            output.write(message.getBytes());
        }
        output.close();
    }
}
