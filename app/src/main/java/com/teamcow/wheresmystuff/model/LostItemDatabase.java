package com.teamcow.wheresmystuff.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teamcow.wheresmystuff.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by WAnya on 6/21/2017.
 */

public class LostItemDatabase extends BaseAdapter {
    private LayoutInflater inflater;
    private static ArrayList<LostItem> itemList;

    private class ViewHolder {
        TextView name;
        TextView descr;
    }

    public LostItemDatabase(Context context, ArrayList<LostItem> item) {
        inflater = LayoutInflater.from(context);
        this.itemList = item;
    }

    public int getCount() {
        return itemList.size();
    }

    public LostItem getItem(int position) {
        return itemList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_item_reg, null);
            holder.name = (TextView) convertView.findViewById(R.id.item_name);
            holder.descr = (TextView) convertView.findViewById(R.id.item_description);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
            holder.name.setText(itemList.get(position).getName());
            holder.descr.setText(itemList.get(position).getDescription());
            return convertView;
    }
    public ArrayList<LostItem> getItemList() {
        return itemList;
    }
}
