package com.teamcow.wheresmystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.LostItem;
import com.teamcow.wheresmystuff.model.LostItemData;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {
    private LostItemData lid = LostItemData.getInstance();
    private ArrayList<LostItem> lostList = lid.getItemList();
    private ArrayList<LostItem> matchingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Intent intent = getIntent();
        String searchKey = intent.getStringExtra("search_key");
        ListView itemDisplay = (ListView) findViewById(R.id.resultView);
        for (LostItem lostItem : lostList) {
            if (searchKey.equals(lostItem.getName().toString())) {
                matchingList.add(lostItem);
            }
        }
        ArrayList<String> itemList = new ArrayList<>();
        for(LostItem item : matchingList) {
            itemList.add(item.getName());
        }
        ArrayAdapter<String> itemAdpt = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, itemList);
        itemDisplay.setAdapter(itemAdpt);
    }
}
