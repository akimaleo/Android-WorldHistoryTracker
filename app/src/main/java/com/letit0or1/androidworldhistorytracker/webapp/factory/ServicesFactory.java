package com.letit0or1.androidworldhistorytracker.webapp.factory;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.letit0or1.androidworldhistorytracker.R;
import com.letit0or1.androidworldhistorytracker.webapp.service.EventService;
import com.letit0or1.androidworldhistorytracker.webapp.service.UserService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by akimaleo on 13.11.16.
 */
public class ServicesFactory {
    private static String URL = "http://192.168.0.114:4567";
    private static ServicesFactory apiFactory = new ServicesFactory();
    private static Retrofit retrofit;
    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int READ_TIMEOUT = 60;
    private static OkHttpClient CLIENT = new OkHttpClient();

    static {
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        b.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        b.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        CLIENT = b.build();
//        URL = "http://" + Resources.getSystem().getString(R.string.server_url) + ":" + Resources.getSystem().getString(R.string.server_port);
    }

    public static ServicesFactory getInstance() {
        if (apiFactory != null)
            return apiFactory;
        return apiFactory = new ServicesFactory();
    }

    @NonNull
    public static Retrofit getRetrofit() {
        if (retrofit != null)
            return retrofit;
        return retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(CLIENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private ServicesFactory() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    //SERVICES
    @NonNull
    public UserService getUserService() {
        return new UserService();
    }

    @NonNull
    public EventService getEventService() {
        return new EventService();
    }
}
