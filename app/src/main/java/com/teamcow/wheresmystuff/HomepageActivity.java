package com.teamcow.wheresmystuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Button homepageLogoutButton = (Button) findViewById(R.id.logout_button);
        Button homepageItemRegButton = (Button) findViewById(R.id.homepage_item_reg_button);
        homepageItemRegButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                goToItemRegister();
            }
        });
        homepageLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogout();
            }
        });
    }

    private void attemptLogout() {
        finish();
    }

    private void goToItemRegister() {
        Intent intent = new Intent(HomepageActivity.this, ItemRegActivity.class);
        startActivity(intent);
    }
}
