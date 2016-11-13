package com.letit0or1.androidworldhistorytracker.database;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Created by akimaleo on 13.11.16.
 */

public class HelperFactory {

    private static DatabaseSQLHelper databaseHelper;

    public static DatabaseSQLHelper getHelper() {
        return databaseHelper;
    }

    public static void setHelper(Context context) {
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseSQLHelper.class);
    }

    public static void releaseHelper() {
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }
}
