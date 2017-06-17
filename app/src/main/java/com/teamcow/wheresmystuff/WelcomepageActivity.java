package com.teamcow.wheresmystuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class WelcomepageActivity extends AppCompatActivity {

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
    }
}
