package com.letit0or1.androidworldhistorytracker.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import com.letit0or1.androidworldhistorytracker.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


}

