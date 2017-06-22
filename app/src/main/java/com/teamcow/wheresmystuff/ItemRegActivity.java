package com.teamcow.wheresmystuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ItemRegActivity extends AppCompatActivity {

    private EditText itemName;
    private EditText itemDescr;
    private LostItemDatabase lid;

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
        itemName = (EditText) findViewById(R.id.item_name);
        itemDescr = (EditText) findViewById(R.id.item_description);
    }

    public void itemRegistration() {
        String name = itemName.getText().toString();
        String descr = itemDescr.getText().toString();
        lid.getItemList().add(new LostItem(name, descr));
        finish();
    }
}
