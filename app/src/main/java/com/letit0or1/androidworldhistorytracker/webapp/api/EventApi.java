package com.letit0or1.androidworldhistorytracker.webapp.api;

import com.letit0or1.androidworldhistorytracker.entity.Event;
import com.letit0or1.androidworldhistorytracker.entity.EventDto;
import com.letit0or1.androidworldhistorytracker.entity.EventSearchDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by akimaleo on 13.11.16.
 */

public interface EventApi {

    @GET("/user/{id}/events/")
    Call<ArrayList<Event>> userEvents(@Body String token);

    @GET("/events/")
    Call<ArrayList<Event>> eventsByParams(@Body EventSearchDto e);

    @POST("/event/add/")
    Call<Void> add(@Body EventDto e);
}
