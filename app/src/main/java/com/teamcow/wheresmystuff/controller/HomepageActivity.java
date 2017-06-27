package com.teamcow.wheresmystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.LostItemData;

/**
 * A page that allows the user to register or search for items
 */
public class HomepageActivity extends AppCompatActivity {
    /**
     * Creates the homepage
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Button homepageLogoutButton = (Button) findViewById(R.id.logout_button);
        Button itemRegButton = (Button) findViewById(R.id.homepage_item_button);
        Button itemSearchButton = (Button) findViewById(R.id.homepage_search_button);
        homepageLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogout();
            }
        });
        itemRegButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                goToItemReg();
            }
        });
        itemSearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                goToItemSearch();
            }
        });
    }

    /**
     * Allows user to exit homepage.
     */
    private void attemptLogout() {
        finish();
    }

    /**
     * Allows user to register items.
     */
    private void goToItemReg() {
        Intent intent = new Intent(HomepageActivity.this, ItemRegActivity.class);
        startActivity(intent);
    }

    /**
     * Allows user to search for lost items.
     */
    private void goToItemSearch() {
        Intent intent = new Intent(HomepageActivity.this, ItemSearchActivity.class);
        startActivity(intent);
    }
}
