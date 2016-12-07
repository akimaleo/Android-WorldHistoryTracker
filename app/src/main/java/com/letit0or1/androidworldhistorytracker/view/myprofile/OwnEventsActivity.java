package com.letit0or1.androidworldhistorytracker.view.myprofile;

import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.letit0or1.androidworldhistorytracker.R;
import com.letit0or1.androidworldhistorytracker.entity.EventIn;
import com.letit0or1.androidworldhistorytracker.entity.EventSearch;
import com.letit0or1.androidworldhistorytracker.view.TokenUtil;
import com.letit0or1.androidworldhistorytracker.view.main.utils.EventsListAdapter;
import com.letit0or1.androidworldhistorytracker.webapp.factory.ServicesFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnEventsActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<EventIn> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_events);

        findViewById(R.id.back_arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLayoutManager = new StaggeredGridLayoutManager(2, Configuration.ORIENTATION_PORTRAIT);
        } else {
            mLayoutManager = new StaggeredGridLayoutManager(4, Configuration.ORIENTATION_PORTRAIT);
        }
        recyclerView.setLayoutManager(mLayoutManager);
        dataset = new ArrayList<EventIn>();
        mAdapter = new EventsListAdapter(dataset);
        recyclerView.setAdapter(mAdapter);
        drawEvents();
    }

    void drawEvents() {
        ServicesFactory.getInstance().getEventService().userEvents(TokenUtil.getOurInstance().getToken()).enqueue(new Callback<ArrayList<EventIn>>() {
            @Override
            public void onResponse(Call<ArrayList<EventIn>> call, Response<ArrayList<EventIn>> response) {
                try {
                    Log.i("Response OwnEvents", response.body().size() + "");
                    setAllEvents(response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<EventIn>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void setAllEvents(ArrayList<EventIn> events) {
        dataset.clear();
        dataset.addAll(events);
        mAdapter.notifyDataSetChanged();
    }
}
