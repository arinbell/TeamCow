package com.teamcow.wheresmystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.LostItem;
import com.teamcow.wheresmystuff.model.LostItemData;

import java.util.ArrayList;

/**
 * A page where users can search for items.
 */
public class ItemSearchActivity extends AppCompatActivity {
    private LostItemData lid = LostItemData.getInstance();
    private ArrayList<LostItem> lostList = lid.getItemList();
    private EditText searchBox;

    /**
     * creates a page where users can search for items that they've lost.
     * @param savedInstanceState saves the instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);
        Button itemSelectButton = (Button) findViewById(R.id.searchpage_search_button);
        Button cancelButton = (Button) findViewById(R.id.searchpage_cancel_button);
        searchBox = (EditText) findViewById(R.id.searchTextBox);
        ListView itemDisplay = (ListView) findViewById(R.id.item_list);

        itemSelectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                Intent intent = new Intent(ItemSearchActivity.this,
                        SearchResultActivity.class);
                intent.putExtra("search_key", searchBox.getText().toString());
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                cancelSearch();
            }
        });

        ArrayList<String> itemList = new ArrayList<>();
        for(LostItem item : lostList) {
            itemList.add(item.getName());
        }
        ArrayAdapter<String> itemAdpt = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, itemList);
        itemDisplay.setAdapter(itemAdpt);
    }

    public void itemSearch(String string) {

    }

    /**
     * allows users to stop searching for items.
     */
    public void cancelSearch() {
        finish();
    }
}
