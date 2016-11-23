package com.letit0or1.androidworldhistorytracker.view.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.letit0or1.androidworldhistorytracker.R;
import com.letit0or1.androidworldhistorytracker.entity.EventAdd;
import com.letit0or1.androidworldhistorytracker.entity.EventSearch;
import com.letit0or1.androidworldhistorytracker.view.TokenUtil;
import com.letit0or1.androidworldhistorytracker.webapp.factory.ServicesFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEventActivity extends Activity {

    private EditText editText;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        editText = (EditText) findViewById(R.id.editText);
        confirmButton = (Button) findViewById(R.id.confirm_button);

        currentBestLocation = getLastBestLocation();
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentBestLocation = getLastBestLocation();
                try {
                    ServicesFactory.getInstance().getEventService().add(
                            new EventAdd(
                                    editText.getText().toString(),
                                    currentBestLocation.getLongitude(),
                                    currentBestLocation.getLatitude()),
                            TokenUtil.getToken(getApplicationContext())).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            finish();
                        }
                    });
                    Toast.makeText(getApplicationContext(), "lon: " + currentBestLocation.getLongitude() + "\nlat: " + currentBestLocation.getLatitude(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "No location detected. Try again;", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    LocationManager mLocationManager;
    private Location currentBestLocation = null;

    private Location getLastBestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNet = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            long GPSLocationTime = 0;
            if (null != locationGPS) {
                GPSLocationTime = locationGPS.getTime();
            }

            long NetLocationTime = 0;

            if (null != locationNet) {
                NetLocationTime = locationNet.getTime();
            }

            if (0 < GPSLocationTime - NetLocationTime) {
                return locationGPS;
            } else {
                return locationNet;
            }
        }
        return null;
    }
}
