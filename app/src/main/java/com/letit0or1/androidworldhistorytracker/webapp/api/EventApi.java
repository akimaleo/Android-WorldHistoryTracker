package com.letit0or1.androidworldhistorytracker.webapp.api;

import com.letit0or1.androidworldhistorytracker.entity.Event;

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
    Call<ArrayList<Event>> userEvents(@Path("id") int id, @Body String token);

    @POST("/event/add/")
    Call<Boolean> add(@Body Event e);
}
