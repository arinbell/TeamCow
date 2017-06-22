package com.teamcow.wheresmystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teamcow.wheresmystuff.R;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Button homepageLogoutButton = (Button) findViewById(R.id.logout_button);
        Button homepageItemRegButton = (Button) findViewById(R.id.homepage_item_reg_button);
        Button homepageItemSearchButton = (Button) findViewById(R.id.homepage_search_button);
        homepageItemRegButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                goToItemRegister();
            }
        });
        homepageItemSearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                goToItemSearch();
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

    private void goToItemSearch() {
        Intent intent = new Intent(HomepageActivity.this, ItemSearchActivity.class);
        startActivity(intent);
    }
}
