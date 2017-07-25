package com.teamcow.wheresmystuff.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.LocalUser;

/**
 * A page that allows the user to register or search for items
 */
public class HomepageActivity extends AppCompatActivity {
    /**
     * Creates the homepage
     * @param savedInstanceState saves the instance state
     */

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Button homepageLogoutButton = (Button) findViewById(R.id.logout_button);
        Button itemRegButton = (Button) findViewById(R.id.homepage_item_button);
        Button itemSearchButton = (Button) findViewById(R.id.homepage_search_button);
        Button mapViewButton = (Button) findViewById(R.id.homepage_maps_button);
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
        mapViewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                goToMapView();
            }
        });
    }

    public static Intent createIntent(
            Context context,
            IdpResponse idpResponse) {
        Intent startIntent = idpResponse == null ? new Intent() : idpResponse.toIntent();

        return startIntent.setClass(context, HomepageActivity.class);
    }

    /**
     * Allows user to exit homepage.
     */
    private void attemptLogout() {
        FirebaseAuth.getInstance().signOut();
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

    /**
     * Allows user to look at map.
     */
    private void goToMapView() {
        Intent intent = new Intent(HomepageActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
