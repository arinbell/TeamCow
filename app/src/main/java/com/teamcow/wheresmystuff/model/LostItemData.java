package com.teamcow.wheresmystuff.model;

import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class LostItemData {
    /** Singleton instance */
    private static final LostItemData lid = new LostItemData();
    public static LostItemData getInstance() {
        return lid;
    }
    private static DatabaseReference mDatabase;

    /** holds the list of all lost items */
    private ArrayList<LostItem> itemList;

    private HashMap<String, LostItem> itemHashMap;

    /** the currently selected item */
    private LostItem currItem;

    /**
     * make a LostItemData class
     */
    public LostItemData() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("lostitems");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                itemList = new ArrayList<LostItem>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    LostItem item = postSnapshot.getValue(LostItem.class);
                    itemList.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void refreshData() {
        //itemList = new ArrayList<>();

    }

    /**
     * get the lost items
     * @return a list of the lost items in the app
     */
    public ArrayList<LostItem> getItemList() {
        return itemList;
    }

    //public HashMap<String, LostItem> getItemHashMap() { return itemHashMap; }

    private class ViewHolder {
        TextView name;
        TextView des;
    }

}
