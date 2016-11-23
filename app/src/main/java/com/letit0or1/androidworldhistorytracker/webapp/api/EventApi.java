package com.letit0or1.androidworldhistorytracker.webapp.api;

import com.letit0or1.androidworldhistorytracker.entity.EventAdd;
import com.letit0or1.androidworldhistorytracker.entity.EventIn;
import com.letit0or1.androidworldhistorytracker.entity.EventSearch;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by akimaleo on 13.11.16.
 */

public interface EventApi {

    @GET("/user/{id}/events/")
    Call<ArrayList<EventIn>> userEvents(@Body String token);

    @POST("/events/with_location/")
    Call<ArrayList<EventIn>> eventsByParams(@Body EventSearch e);

    @POST("/events/create/")
    Call<Void> add(@Body EventAdd e, @Header("Authorization") String token);
}
