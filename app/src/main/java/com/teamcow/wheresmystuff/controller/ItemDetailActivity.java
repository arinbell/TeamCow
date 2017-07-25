package com.teamcow.wheresmystuff.controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.ItemType;
import com.teamcow.wheresmystuff.model.LostItem;
import com.teamcow.wheresmystuff.model.PosterType;

public class ItemDetailActivity extends AppCompatActivity {

    private LostItem lostItem;

    private FloatingActionButton fab;
    private TextView desTextView;
    private Button mapViewButton;


    private final String INTENT_ITEM = "item_to_view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        desTextView = (TextView) findViewById(R.id.description_textview);
        mapViewButton = (Button) findViewById(R.id.view_map_button);

        fab = (FloatingActionButton) findViewById(R.id.fab_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //lostItem = new LostItem("", "", "", "", 0, 0, ItemType.ELECTRONIC, PosterType.FINDER, );

        if (getIntent().hasExtra(INTENT_ITEM)) {
            lostItem = getIntent().getParcelableExtra(INTENT_ITEM);
            if (lostItem != null) {
                fillDisplay();
            }
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (lostItem.getUser().equals(user.getUid())) {
            findViewById(R.id.fab_edit).setEnabled(true);
            findViewById(R.id.fab_edit).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.fab_edit).setEnabled(false);
            findViewById(R.id.fab_edit).setVisibility(View.INVISIBLE);
        }
    }

    private void fillDisplay() {
    }
}
