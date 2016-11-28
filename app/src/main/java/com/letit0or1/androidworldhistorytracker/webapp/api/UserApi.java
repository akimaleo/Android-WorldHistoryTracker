package com.letit0or1.androidworldhistorytracker.webapp.api;

import com.letit0or1.androidworldhistorytracker.entity.User;
import com.letit0or1.androidworldhistorytracker.entity.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by akimaleo on 13.11.16.
 */

public interface UserApi {
    @GET("/user/get_by_token/")
    Call<String> check(@Header("Authorization") String token);

    @POST("/user/auth/")
    Call<Void> authorization(@Body UserDto user);

    @POST("/user/reg/")
    Call<Void> registration(@Body UserDto user);
}
