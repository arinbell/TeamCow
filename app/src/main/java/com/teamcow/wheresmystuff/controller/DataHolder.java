package com.teamcow.wheresmystuff.controller;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.teamcow.wheresmystuff.R;

/**
 * Created by arinb on 7/27/2017.
 */

public class DataHolder extends RecyclerView.ViewHolder{
    private final TextView nName;
    private final TextView nDesc;

    public DataHolder (View itemView) {
        super(itemView);
        nName = (TextView) itemView.findViewById(R.id.tvName);
        nDesc = (TextView) itemView.findViewById(R.id.tvDesc);
    }

    public void setnName(String name) {
        nName.setText(name);
    }

    public void setnDesc(String desc) {
        nDesc.setText(desc);
    }
}
