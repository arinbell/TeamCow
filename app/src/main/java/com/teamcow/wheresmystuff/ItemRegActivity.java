package com.teamcow.wheresmystuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ItemRegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_reg);
        Button ItemRegButton = (Button) findViewById(R.id.item_reg_button);
        ItemRegButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                itemRegistration();
            }
        });
    }

    public void itemRegistration() {

    }
}
