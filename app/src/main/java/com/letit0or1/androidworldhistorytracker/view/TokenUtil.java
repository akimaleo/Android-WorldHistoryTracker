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
    private Context context;

    public String getToken() {
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sPref.getString("token", "none");
    }

    public static TokenUtil getOurInstance() {
        return ourInstance;
    }

    public void setToken(String token) {
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("token", token);
        ed.apply();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUsername() {
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sPref.getString("username", "none");
    }

    public void setUsername(String username) {
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("username", username);
        ed.apply();
    }

    private TokenUtil() {
    }
}
