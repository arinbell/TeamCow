package com.teamcow.wheresmystuff.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.LostItem;
import com.teamcow.wheresmystuff.model.LostItemData;

import java.util.ArrayList;
import java.util.List;

public class ItemSearchActivity extends AppCompatActivity {
    private LostItemData lid = LostItemData.getInstance();
    private ArrayList<LostItem> lostList = lid.getItemList();
    private ArrayList<String> itemL = new ArrayList<String>();
    private ListView itemDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);
        Button itemSelectButton = (Button) findViewById(R.id.searchpage_search_button);
        Button cancelButton = (Button) findViewById(R.id.searchpage_cancel_button);
        ListView itemDisplay = (ListView) findViewById(R.id.item_list);

        itemSelectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                itemSearch();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                cancelSearch();
            }
        });

        for(LostItem item : lostList) {
            itemL.add(item.getName() + ", " + item.getDescription());
        }
    }

    public void itemSearch() {
        ArrayAdapter<String> arrayAdpt = new ArrayAdapter<String>(this,
                R.layout.activity_item_search, itemL);
        itemDisplay.setAdapter(arrayAdpt);

    }

    public void cancelSearch() {
        finish();
    }
}
