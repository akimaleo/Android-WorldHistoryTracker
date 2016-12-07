package com.letit0or1.androidworldhistorytracker;

import com.letit0or1.androidworldhistorytracker.view.TokenUtil;

/**
 * Created by akimaleo on 03.12.16.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TokenUtil.getOurInstance().setContext(getApplicationContext());
    }
}
