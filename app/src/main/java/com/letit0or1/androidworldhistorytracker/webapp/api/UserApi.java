package com.letit0or1.androidworldhistorytracker.webapp.api;

import com.letit0or1.androidworldhistorytracker.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by akimaleo on 13.11.16.
 */

public interface UserApi {

    @GET("/user/{id}")
    Call<User> getById(@Path("id") int id);

    @POST("/user/authorization/")
    Call<User> authorization(@Body User user);

    @POST("/user/registration/")
    Call<User> registration(@Body User user);
}
