package com.teamcow.wheresmystuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ItemRegActivity extends AppCompatActivity {

    private EditText itemName;
    private EditText itemDescr;
    private LostItemDatabase lid;
    private ListView LostItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_reg);
        Button ItemRegButton = (Button) findViewById(R.id.item_reg_button);
        LostItemList= (ListView) findViewById(R.id.lost_item_list);
        ItemRegButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                itemRegistration();
            }
        });
        itemName = (EditText) findViewById(R.id.item_name);
        itemDescr = (EditText) findViewById(R.id.item_description);
    }

    public void itemRegistration() {
        ArrayList<LostItem> item = new ArrayList<LostItem>();
        LostItemDatabase lostItemD = new LostItemDatabase(this, item);
        LostItemList.setAdapter(lostItemD);
        setContentView(R.layout.activity_homepage);
    }
}
