package com.teamcow.wheresmystuff.controller;

/**
 * Created by arinb on 7/27/2017.
 */

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.LostItem;

import java.util.List;



public class DataAdapterTemp extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private List<LostItem> itemList;

    // The minimum amount of items to have below your current scroll position
// before loading more.
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;


    public DataAdapterTemp(List<LostItem> lostItems, RecyclerView recyclerView) {
        itemList = lostItems;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();


            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                // End has been reached
                                // Do something
                                if (onLoadMoreListener != null) {
                                    onLoadMoreListener.onLoadMore();
                                }
                                loading = true;
                            }
                        }
                    });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item, parent, false);

            vh = new LostitemViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.progressbar, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LostitemViewHolder) {

            LostItem singleItem = (LostItem) itemList.get(position);

            ((LostitemViewHolder) holder).tvName.setText(singleItem.getName());

            ((LostitemViewHolder) holder).tvDesc.setText(singleItem.getDescription());

            ((LostitemViewHolder) holder).lostItem = singleItem;

        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }


    //
    public static class LostitemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public TextView tvDesc;

        public LostItem lostItem;

        public LostitemViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tvName);

            tvDesc = (TextView) v.findViewById(R.id.tvDesc);

            v.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(),
//                            "OnClick :" + lostItem.getName() + " \n "+lostItem.getDescription(),
//                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(tvName.getContext(), ItemDetailActivity.class);
                    intent.putExtra("lostitem", lostItem);
                    tvName.getContext().startActivity(intent);
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
