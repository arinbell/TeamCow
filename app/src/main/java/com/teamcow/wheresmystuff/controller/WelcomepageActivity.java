package com.teamcow.wheresmystuff.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.teamcow.wheresmystuff.R;

/**
 * A launch page that allows users to login or register.
 */
public class WelcomepageActivity extends AppCompatActivity {
    /**
     * creates welcome page
     * @param savedInstanceState saves the instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);

        Button advanceToLogin = (Button) findViewById(R.id.login);
        advanceToLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomepageActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button advanceToRegister = (Button) findViewById(R.id.register);
        advanceToRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomepageActivity.this, RegisterAccount.class);
                startActivity(intent);
            }
        });
    }
}
