package com.teamcow.wheresmystuff.model;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class LostItemData {
    /** Singleton instance */
    private static final LostItemData lid = new LostItemData();
    public static LostItemData getInstance() {
        return lid;
    }

    /** holds the list of all lost items */
    private ArrayList<LostItem> itemList;

    private HashMap<String, LostItem> itemHashMap;

    /** the currently selected item */
    private LostItem currItem;

    /**
     * make a LostItemData class
     */
    public LostItemData() {
        itemList = new ArrayList<>();
        itemHashMap = new HashMap<>();
    }
    /**
     * get the lost items
     * @return a list of the lost items in the app
     */
    public ArrayList<LostItem> getItemList() {
        return itemList;
    }

    public HashMap<String, LostItem> getItemHashMap() { return itemHashMap; }

    private class ViewHolder {
        TextView name;
        TextView des;
    }

}
