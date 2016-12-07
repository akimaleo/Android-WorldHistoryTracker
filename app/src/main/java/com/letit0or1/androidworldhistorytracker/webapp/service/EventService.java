package com.letit0or1.androidworldhistorytracker.webapp.service;

import com.letit0or1.androidworldhistorytracker.entity.EventAdd;
import com.letit0or1.androidworldhistorytracker.entity.EventIn;
import com.letit0or1.androidworldhistorytracker.entity.EventSearch;
import com.letit0or1.androidworldhistorytracker.webapp.api.EventApi;
import com.letit0or1.androidworldhistorytracker.webapp.factory.ServicesFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;

/**
 * Created by akimaleo on 13.11.16.
 */

public class EventService implements EventApi {


    @Override
    public Call<ArrayList<EventIn>> userEvents(@Header("Authorization") String token) {
        EventApi eApi = ServicesFactory.getRetrofit().create(EventApi.class);
        return eApi.userEvents(token);
    }

    @Override
    public Call<ArrayList<EventIn>> eventsByParams(@Body EventSearch e) {
        EventApi eApi = ServicesFactory.getRetrofit().create(EventApi.class);
        e.setRadius(e.getRadius());//from radius in KM to radius in LnLon
        return eApi.eventsByParams(e);
    }

    @Override
    public Call<Void> add(@Body EventAdd e, String token) {
        EventApi eApi = ServicesFactory.getRetrofit().create(EventApi.class);
        return eApi.add(e, token);
    }
}
