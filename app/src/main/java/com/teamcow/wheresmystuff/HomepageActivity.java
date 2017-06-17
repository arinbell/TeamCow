package com.teamcow.wheresmystuff;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by WAnya on 6/16/2017.
 */

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button homepageLogoutButton = (Button) findViewById(R.id.logout_button);
        homepageLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogout();
            }
        });

    }

    private void attemptLogout() {
        setContentView(R.layout.activity_login);
    }
}
