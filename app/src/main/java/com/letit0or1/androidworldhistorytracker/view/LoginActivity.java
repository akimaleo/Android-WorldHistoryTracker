package com.letit0or1.androidworldhistorytracker.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import com.letit0or1.androidworldhistorytracker.R;
import com.letit0or1.androidworldhistorytracker.database.DatabaseSQLHelper;
import com.letit0or1.androidworldhistorytracker.database.HelperFactory;
import com.letit0or1.androidworldhistorytracker.entity.Event;

import java.sql.SQLException;
import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            HelperFactory.setHelper(this);
            List<Event> a = HelperFactory.getHelper().getEventDAO().getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Intent intent = new Intent(this, MapsActivity.class);
//        startActivity(intent);
    }

}

