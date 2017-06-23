package com.teamcow.wheresmystuff.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teamcow.wheresmystuff.R;

import java.util.ArrayList;

/**
 * Created by WAnya on 6/22/2017.
 */

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
        itemList = new ArrayList<LostItem>();

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
