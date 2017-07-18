package com.teamcow.wheresmystuff.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.ItemType;
import com.teamcow.wheresmystuff.model.LostItem;
import com.teamcow.wheresmystuff.model.LostItemData;
import com.teamcow.wheresmystuff.model.PosterType;

/**
 * A page where users can register items.
 */
@SuppressWarnings("ALL")
public class ItemRegActivity extends AppCompatActivity {
    private LostItemData lid = LostItemData.getInstance();
    private EditText itemNF;
    private EditText itemDF;
    private EditText itemX;
    private EditText itemY;
    private Spinner itemTypeSpinner;
    private Spinner posterTypeSpinner;

    /**
     * creates a page where users can register items they've found.
     * @param savedInstanceState saves the instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_reg);
        Button itemPostButton = (Button) findViewById(R.id.itemreg_post_button);
        itemNF = (EditText)findViewById(R.id.item_name_field);
        itemDF = (EditText)findViewById(R.id.item_des_field);
        itemTypeSpinner = (Spinner)findViewById(R.id.item_type_spinner);
        posterTypeSpinner = (Spinner)findViewById(R.id.poster_type_spinner);
        itemX = (EditText)findViewById(R.id.x_coordinate);
        itemY = (EditText)findViewById(R.id.y_coordinate);

        //Set up the adapter to display the item types in the spinner
        ArrayAdapter<String> adapter_1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                ItemType.values());
        adapter_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemTypeSpinner.setAdapter(adapter_1);

        //Set up the adapter to display the user types in the spinner
        ArrayAdapter<String> adapter_2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                PosterType.values());
        adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        posterTypeSpinner.setAdapter(adapter_2);

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
        String itemX_string = itemX.getText().toString();
        String itemY_string = itemY.getText().toString();
        double itemX_coordinate = Double.parseDouble(itemX_string);
        double itemY_coordinate = Double.parseDouble(itemY_string);

        if(itemX_string.length() == 0 || itemY_string.length() == 0)
        {
            lid.getItemList().add(new LostItem(itemNF.getText().toString(), itemDF.getText().toString(),
                    (ItemType) itemTypeSpinner.getSelectedItem(),
                    (PosterType) posterTypeSpinner.getSelectedItem()));
            finish();
        }
        else
        {
            lid.getItemList().add(new LostItem(itemNF.getText().toString(), itemDF.getText().toString(),
                    (ItemType) itemTypeSpinner.getSelectedItem(),
                    (PosterType) posterTypeSpinner.getSelectedItem(), itemX_coordinate,
                    itemY_coordinate));
            finish();
        }
    }
}
