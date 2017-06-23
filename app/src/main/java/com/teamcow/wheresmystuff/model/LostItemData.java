package com.teamcow.wheresmystuff.model;

import java.util.ArrayList;

/**
 * Created by WAnya on 6/22/2017.
 */

public class LostItemData {
    /** Singleton instance */
    public static final LostItemData lid = new LostItemData();
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
        itemList = new ArrayList<LostItem>();

    }
    /**
     * get the lost items
     * @return a list of the lost items in the app
     */
    public ArrayList<LostItem> getItemList() {
        return itemList;
    }
}
