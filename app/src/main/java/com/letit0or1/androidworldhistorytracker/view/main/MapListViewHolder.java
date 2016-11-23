package com.letit0or1.androidworldhistorytracker.view.main;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.letit0or1.androidworldhistorytracker.R;
import com.letit0or1.androidworldhistorytracker.entity.EventIn;
import com.letit0or1.androidworldhistorytracker.entity.EventSearch;
import com.letit0or1.androidworldhistorytracker.view.main.utils.EventsListAdapter;
import com.letit0or1.androidworldhistorytracker.webapp.factory.ServicesFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapListViewHolder extends AppCompatActivity implements OnMapReadyCallback, DatePickerDialog.OnDateSetListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<EventIn> dataset;

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;

    //    private ActionBar mActionBar;
    private Drawable upArrow;

    private boolean isAnimated = false;
    private Circle circle;
    CircleOptions c;
    //toolbar
    private TextView title;
    private ImageButton arrow_button;

    private SeekBar seekRadius;
    private TextView radiusText;
    private TextView timeFilter;
    private FloatingActionButton floatingActionButton;

    private Timestamp from, to;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_list_view_holder);

        from = new Timestamp(0);
        to = new Timestamp(2017, 1, 1, 1, 1, 1, 1);

        timeFilter = (TextView) findViewById(R.id.filter_calendar);
//        mActionBar = getSupportActionBar();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        upArrow = getResources().getDrawable(R.drawable.ic_arrow_back);
        upArrow.setColorFilter(getResources().getColor(R.color.actionbarTransparent), PorterDuff.Mode.SRC_ATOP);
//        mActionBar.setHomeAsUpIndicator(upArrow);
//        mActionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView.setHasFixedSize(true);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLayoutManager = new StaggeredGridLayoutManager(2, Configuration.ORIENTATION_PORTRAIT);
        } else {
            mLayoutManager = new StaggeredGridLayoutManager(4, Configuration.ORIENTATION_PORTRAIT);
        }
//        mLayoutManager = new StaggeredGridLayoutManager(2, getResources().getConfiguration().orientation);
        recyclerView.setLayoutManager(mLayoutManager);
        dataset = new ArrayList<EventIn>();
        mAdapter = new EventsListAdapter(dataset);
        recyclerView.setAdapter(mAdapter);

//        map
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        init();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
                    startActivity(intent);
                } else {
                    // Permission Denied
                    Toast.makeText(getApplicationContext(), "No permission granted", Toast.LENGTH_LONG);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        timeFilter.setText("From: " + dayOfMonth + "." + monthOfYear + "." + year + "\nTo:      " + dayOfMonthEnd + "." + monthOfYearEnd + "." + yearEnd);

        from = new Timestamp(year - 1900, monthOfYear, dayOfMonth, 0, 0, 0, 0);
        to = new Timestamp(yearEnd - 1900, monthOfYearEnd, dayOfMonthEnd, 23, 59, 59, 0);
        drawEvents();
    }

    //Dialogs
    void showCalendarDialogListener() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                MapListViewHolder.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    void goAddEvent() {
        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        }
    }

    //Map
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                c = new CircleOptions();
                c.center(latLng);
                c.fillColor(Color.argb(8, 0, 0, 0));
                c.radius(seekRadius.getProgress() * 1000);
                if (circle != null)
                    circle.remove();
                circle = mMap.addCircle(c);
                drawEvents();
            }
        });
    }

    void init() {
        title = (TextView) findViewById(R.id.title);
        arrow_button = (ImageButton) findViewById(R.id.back_arrow);
        arrow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSwap();
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goAddEvent();
            }
        });
        timeFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendarDialogListener();
            }
        });
        radiusText = (TextView) findViewById(R.id.radius);
        radiusText.setText("70");
        seekRadius = (SeekBar) findViewById(R.id.seek_bar_radius);
        seekRadius.setProgress(70);
        seekRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                radiusText.setText(seekBar.getProgress() + " km");
                if (circle != null)
                    circle.setRadius(seekBar.getProgress() * 1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                drawEvents();
            }
        });
    }

    void drawEvents() {

        if (circle != null) {
            mMap.clear();
            circle = mMap.addCircle(c);
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    marker.showInfoWindow();
                    return true;
                }
            });

            ServicesFactory.getInstance().getEventService().eventsByParams(
                    new EventSearch(circle.getCenter().latitude, circle.getCenter().longitude, seekRadius.getProgress(), from, to))
                    .enqueue(new Callback<ArrayList<EventIn>>() {
                        @Override
                        public void onResponse(Call<ArrayList<EventIn>> call, Response<ArrayList<EventIn>> response) {

                            if (response.body() != null) {
                                setAllEvents(response.body());
                            } else
                                Log.e("GET EVENTS", "NULL EVENTS");
                        }

                        @Override
                        public void onFailure(Call<ArrayList<EventIn>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "oopsi", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void setAllEvents(ArrayList<EventIn> events) {
        Log.e("DRAW EVENTS", "EVENTS COUNT: " + events.size());

        for (int i = 0; i < events.size(); i++) {
            Log.i("EVENT NAME: ", events.get(i).getEventName());
            MarkerOptions curMarker = new MarkerOptions();
            curMarker.draggable(false);
            curMarker.position(new LatLng(events.get(i).getLatitude(), events.get(i).getLongitude()));
            curMarker.title(events.get(i).getEventName());
            mMap.addMarker(curMarker);
        }

    }

    //Swap list and map
    void setMap() {
        circularAnimHide(recyclerView);
        title.setText("Tap on map and change radius");

    }

    void setList() {
        circularAnimShow(recyclerView);
        title.setText("List of events");
    }

    void onSwap() {
        if (isAnimated)
            return;

        boolean isList = recyclerView.getVisibility() == View.VISIBLE; //viewFlipper.getCurrentView() instanceof RecyclerView;
        if (isList) {
            setMap();
            Animation rotation = AnimationUtils.loadAnimation(this, R.anim.arrow_anim_to_left);
            arrow_button.startAnimation(rotation);
//            item.getActionView()
//                    .getRootView()
//                    .animate()
//                    .setDuration(200)
//                    .setInterpolator(new BounceInterpolator())
//                    .rotation(180)
//                    .start();
        } else {
            setList();
            Animation rotation = AnimationUtils.loadAnimation(this, R.anim.arrow_anim_to_right);
            arrow_button.startAnimation(rotation);
//            item.getActionView()
//                    .animate()
//                    .setDuration(200)
//                    .setInterpolator(new BounceInterpolator())
//                    .rotation(0)
//                    .start();
        }
    }

    private void circularAnimShow(View showView) {
//        int cx = (showView.getLeft() + showView.getRight()) / 2;
//        int cy = (showView.getTop() + showView.getBottom()) / 2;
        int cx = showView.getLeft();
        int cy = showView.getTop();
// get the final seekRadius for the clipping circle
        int finalRadius = Math.max(showView.getWidth(), showView.getHeight());

// create the animator for this view (the start seekRadius is zero)
        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(showView, cx, cy, 0, finalRadius);
        }

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
        // get the initial seekRadius for the clipping circle
        int initialRadius = Math.max(hideView.getWidth(), hideView.getHeight());

        // create the animation (the final seekRadius is zero)
        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(hideView, cx, cy, initialRadius, 0);
        }

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
    public void onBackPressed() {
        onSwap();
    }
}
