package com.teamcow.wheresmystuff.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.User;
import com.teamcow.wheresmystuff.model.UserType;

public class UserInfoActivity extends AppCompatActivity {
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Button userSaveButton = (Button) findViewById(R.id.saveuserinfo);
        Button userCancelButton = (Button) findViewById(R.id.canceluserinfo);

        userSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                saveUserInfo();
            }
        });

        userCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                cancelUserInfo();
            }
        });
    }

    public void saveUserInfo() {
        EditText userText = (EditText)findViewById(R.id.text_username);
        EditText passText = (EditText)findViewById(R.id.text_password);
        EditText zipText = (EditText)findViewById(R.id.text_zipcode);
        EditText phoneText = (EditText)findViewById(R.id.text_phonenum);
        EditText stateText = (EditText)findViewById(R.id.text_state);
        //user = new User(String id, String pass, UserType userT, String zipCode,
         //       String city, String phoneNumber, String state)
    }

    public void cancelUserInfo() {
        finish();
    }
}
