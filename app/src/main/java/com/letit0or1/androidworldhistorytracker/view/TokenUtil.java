package com.letit0or1.androidworldhistorytracker.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by akimaleo on 22.11.16.
 */
public class TokenUtil {

    private static TokenUtil ourInstance = new TokenUtil();

    public static String getToken(Context context) {
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sPref.getString("token", "none");
    }

    public static void setToken(Context context, String token) {
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("token", token);
        ed.apply();
    }

    public static String getUsername(Context context) {
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sPref.getString("username", "none");
    }

    public static void setUsername(Context context, String username) {
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("username", username);
        ed.apply();
    }

    private TokenUtil() {
    }
}
