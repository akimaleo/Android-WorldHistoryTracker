package com.letit0or1.androidworldhistorytracker.webapp.service;

import com.letit0or1.androidworldhistorytracker.entity.EventDto;
import com.letit0or1.androidworldhistorytracker.entity.EventSearchDto;
import com.letit0or1.androidworldhistorytracker.webapp.api.EventApi;
import com.letit0or1.androidworldhistorytracker.entity.Event;
import com.letit0or1.androidworldhistorytracker.webapp.factory.ServicesFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Path;

/**
 * Created by akimaleo on 13.11.16.
 */

public class EventService implements EventApi {

    @Override
    public Call<ArrayList<Event>> userEvents(@Body String token) {
        EventApi eApi = ServicesFactory.getRetrofit().create(EventApi.class);
        Call<ArrayList<Event>> call = eApi.userEvents(token);
        return call;
    }

    @Override
    public Call<ArrayList<Event>> eventsByParams(@Body EventSearchDto e) {
        EventApi eApi = ServicesFactory.getRetrofit().create(EventApi.class);
        return eApi.eventsByParams(e);
    }

    @Override
    public Call<Void> add(@Body EventDto e) {
        EventApi eApi = ServicesFactory.getRetrofit().create(EventApi.class);
        return eApi.add(e);
    }
}
