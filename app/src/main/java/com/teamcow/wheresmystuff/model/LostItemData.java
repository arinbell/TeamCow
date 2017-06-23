package com.teamcow.wheresmystuff.model;

import java.util.ArrayList;

/**
 * Created by WAnya on 6/22/2017.
 */

public class LostItemData {
    public static final LostItemData lid = new LostItemData();
    public static LostItemData getInstance() {
        return lid;
    }

    private ArrayList<LostItem> itemList;

    private LostItem currItem;

    public LostItemData() {
        itemList = new ArrayList<LostItem>();

    }

    public ArrayList<LostItem> getItemList() {
        return itemList;
    }
}
