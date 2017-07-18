package com.teamcow.wheresmystuff.model;

import android.widget.TextView;

import java.util.ArrayList;

public class LostItemData {
    /** Singleton instance */
    private static final LostItemData lid = new LostItemData();
    public static LostItemData getInstance() {
        return lid;
    }

    /** holds the list of all lost items */
    private ArrayList<LostItem> itemList;

    /** the currently selected item */
    private LostItem currItem;

    /**
     * make a LostItemData class
     */
    public LostItemData() {
        itemList = new ArrayList<>();

    }
    /**
     * get the lost items
     * @return a list of the lost items in the app
     */
    public ArrayList<LostItem> getItemList() {
        return itemList;
    }

    private class ViewHolder {
        TextView name;
        TextView des;
    }

}
