package com.teamcow.wheresmystuff.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.LostItem;
import com.teamcow.wheresmystuff.model.LostItemData;


public class ItemRegActivity extends AppCompatActivity {
    private LostItemData lid = LostItemData.getInstance();
    EditText itemNF;
    EditText itemDF;
    ListView itemL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_reg);
        Button itemPostButton = (Button) findViewById(R.id.itemreg_post_button);

        itemPostButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                postItem();
            }
        });
    }
    /**
     * adds a new item to a preexisting list of items
     */
    public void postItem() {
        EditText itemNF = (EditText)findViewById(R.id.item_name_field);
        EditText itemDF = (EditText)findViewById(R.id.item_des_field);
        lid.getItemList().add(new LostItem(itemNF.getText().toString(),
                        itemDF.getText().toString()));
        finish();
    }
}
