package com.letit0or1.androidworldhistorytracker.view.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.letit0or1.androidworldhistorytracker.R;
import com.letit0or1.androidworldhistorytracker.view.main.utils.EventsListAdapter;

import java.util.Calendar;

public class MapListViewHolder extends AppCompatActivity implements OnMapReadyCallback, DatePickerDialog.OnDateSetListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;

    private RelativeLayout rootView;

    private ActionBar mActionBar;
    private Drawable upArrow;

    private boolean isAnimated = false;
    private Circle circle;

    void showCalendarDialogListener(){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                MapListViewHolder.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_list_view_holder);

        mActionBar = getSupportActionBar();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        rootView = (RelativeLayout) findViewById(R.id.activity_map_list_view_holder);

        upArrow = getResources().getDrawable(R.drawable.ic_arrow_back);
        upArrow.setColorFilter(getResources().getColor(R.color.actionbarTransparent), PorterDuff.Mode.SRC_ATOP);
        mActionBar.setHomeAsUpIndicator(upArrow);
        mActionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(2, getResources().getConfiguration().orientation);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventsListAdapter(new String[]{
                "Such a long. Event description. Such a long. Event description. Such a long. Event description.",
                "Such a long. Event description.",
                "Such a long. Event description.",
                "Event description "});

        recyclerView.setAdapter(mAdapter);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        init();
    }


    void setMap() {
        circularAnimHide(recyclerView);
        mActionBar.setTitle("Tap on map");
//        mActionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.actionbarTansparant)));

    }

    void setList() {
        circularAnimShow(recyclerView);
        mActionBar.setTitle("Event list");
    }


    void init() {

        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                setList();
            }
        });
    }

    void onSwap(View item) {
        if (isAnimated)
            return;
        boolean isList = recyclerView.getVisibility() == View.VISIBLE; //viewFlipper.getCurrentView() instanceof RecyclerView;
        if (isList) {
            setMap();
            Animation rotation = AnimationUtils.loadAnimation(this, R.anim.arrow_anim_to_left);
            if (item != null)
                item.startAnimation(rotation);
//            item.getActionView()
//                    .getRootView()
//                    .animate()
//                    .setDuration(200)
//                    .setInterpolator(new BounceInterpolator())
//                    .rotation(180)
//                    .start();
        } else {
            setList();
            Animation rotation = AnimationUtils.loadAnimation(this, R.anim.arrow_anim_to_left);
            if (item != null)
                item.startAnimation(rotation);
//            item.getActionView()
//                    .animate()
//                    .setDuration(200)
//                    .setInterpolator(new BounceInterpolator())
//                    .rotation(0)
//                    .start();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                CircleOptions c = new CircleOptions();
                c.center(latLng);
                c.fillColor(Color.BLUE);
                c.radius(100000);
                if (circle != null)
                    circle.remove();
                circle = mMap.addCircle(c);
            }
        });

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void circularAnimShow(View showView) {
//        int cx = (showView.getLeft() + showView.getRight()) / 2;
//        int cy = (showView.getTop() + showView.getBottom()) / 2;
        int cx = showView.getLeft();
        int cy = showView.getTop();
// get the final radius for the clipping circle
        int finalRadius = Math.max(showView.getWidth(), showView.getHeight());

// create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(showView, cx, cy, 0, finalRadius);

        showView.setVisibility(View.VISIBLE);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isAnimated = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimated = false;
            }
        });
        anim.start();
    }

    private void circularAnimHide(final View hideView) {

        // get the center for the clipping circle
//        int cx = (hideView.getLeft() + hideView.getRight()) / 2;
//        int cy = (hideView.getTop() + hideView.getBottom()) / 2;
        int cx = hideView.getRight();
        int cy = hideView.getTop();
        // get the initial radius for the clipping circle
        int initialRadius = Math.max(hideView.getWidth(), hideView.getHeight());

        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(hideView, cx, cy, initialRadius, 0);

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isAnimated = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                hideView.setVisibility(View.INVISIBLE);
                isAnimated = false;
            }
        });

        anim.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                item.setActionView(R.layout.arrow_back_action);
                onSwap(item.getActionView());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.getItem(R.id.).setActionView(R.layout.arrow_back_action);

        return super.onCreateOptionsMenu(menu);
    }

}
