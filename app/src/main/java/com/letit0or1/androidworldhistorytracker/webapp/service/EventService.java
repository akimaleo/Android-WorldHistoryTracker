package com.letit0or1.androidworldhistorytracker.webapp.service;

import com.letit0or1.androidworldhistorytracker.webapp.api.EventApi;
import com.letit0or1.androidworldhistorytracker.entity.Event;
import com.letit0or1.androidworldhistorytracker.webapp.factory.ServicesFactory;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by akimaleo on 13.11.16.
 */

public class EventService {
    public Call<ArrayList<Event>> getById(int id, String token) {
        EventApi eApi = ServicesFactory.getRetrofit().create(EventApi.class);
//        String locale = Resources.getSystem().getConfiguration().locale.toString();
        Call<ArrayList<Event>> call = eApi.userEvents(id, token);
        return call;
    }

    public boolean add(Event e) {
        EventApi eApi = ServicesFactory.getRetrofit().create(EventApi.class);
        try {
            eApi.add(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
