package com.teamcow.wheresmystuff.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teamcow.wheresmystuff.R;

public class ItemSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);
        Button itemSelectButton = (Button) findViewById(R.id.searchpage_select_button);
        Button cancelButton = (Button) findViewById(R.id.searchpage_cancel_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                cancelSearch();
            }
        });
    }

    public void cancelSearch() {
        finish();
    }
}
