package com.letit0or1.androidworldhistorytracker.view.main.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.letit0or1.androidworldhistorytracker.R;
import com.letit0or1.androidworldhistorytracker.entity.Event;
import com.letit0or1.androidworldhistorytracker.entity.EventIn;

import java.util.ArrayList;

/**
 * Created by akimaleo on 15.11.16.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {

    private ArrayList<EventIn> mDataset;


    public EventsListAdapter(ArrayList<EventIn> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public EventsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_event, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextDescription.setText(mDataset.get(position).getEventName());
        holder.mTextTime.setText(mDataset.get(position).getCreateDate().toString());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextDescription;
        public TextView mTextTime;

        public ViewHolder(View v) {
            super(v);
            mTextDescription = (TextView) v.findViewById(R.id.event_description);
            mTextTime = (TextView) v.findViewById(R.id.time_text);
        }
    }
}