package com.teamcow.wheresmystuff.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.teamcow.wheresmystuff.R;

public class ItemSearchActivity extends AppCompatActivity {
    //private LostItemDatabase lostItemD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);
        ListView LostItemList = (ListView) findViewById(R.id.lost_item_list);
    }

    
}
