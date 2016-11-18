package com.letit0or1.androidworldhistorytracker.view.main.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.letit0or1.androidworldhistorytracker.R;
import com.letit0or1.androidworldhistorytracker.entity.Event;

import java.util.ArrayList;

/**
 * Created by akimaleo on 15.11.16.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {

    private ArrayList<Event> mDataset;


    // Provide a suitable constructor (depends on the kind of dataset)
    public EventsListAdapter(ArrayList<Event> mDataset) {
        this.mDataset = mDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_event, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextDescription.setText(mDataset.get(position).getContent());
        holder.mTextTime.setText(mDataset.get(position).getDateTime().toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextDescription;
        public TextView mTextTime;

        public ViewHolder(View v) {
            super(v);
            mTextDescription = (TextView) v.findViewById(R.id.event_description);
            mTextTime = (TextView) v.findViewById(R.id.time_text);
        }
    }
}