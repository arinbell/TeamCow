package com.teamcow.wheresmystuff.controller;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.LostItem;

import java.util.List;

/**
 * Created by arinb on 7/27/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.LostitemViewHolder> {
    private List<LostItem> mDataset;

//    // Provide a reference to the views for each data item
//    // Complex data items may need more than one view per item, and
//    // you provide access to all the views for a data item in a view holder
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        // each data item is just a string in this case
//        public TextView mTextView;
//        public ViewHolder(TextView v) {
//            super(v);
//            mTextView = v;
//        }
//    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DataAdapter(List<LostItem> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DataAdapter.LostitemViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
//        TextView v = (TextView) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        DataAdapter.LostitemViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item, parent, false);

        vh = new DataAdapter.LostitemViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DataAdapter.LostitemViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        LostItem singleItem = (LostItem) mDataset.get(position);
        holder.tvName.setText(singleItem.getName());

        holder.tvDesc.setText(singleItem.getDescription());

        holder.lostItem = singleItem;

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class LostitemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public TextView tvDesc;

        public LostItem lostItem;

        public LostitemViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tvName);

            tvDesc = (TextView) v.findViewById(R.id.tvDesc);

            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(),
//                            "OnClick :" + lostItem.getName() + " \n "+lostItem.getDescription(),
//                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), ItemDetailActivity.class);
                    intent.putExtra("item_to_view", lostItem);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }
}