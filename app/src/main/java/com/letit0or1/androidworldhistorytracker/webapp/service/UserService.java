package com.letit0or1.androidworldhistorytracker.webapp.service;

import com.letit0or1.androidworldhistorytracker.entity.UserDto;
import com.letit0or1.androidworldhistorytracker.webapp.api.UserApi;
import com.letit0or1.androidworldhistorytracker.webapp.factory.ServicesFactory;

import retrofit2.Call;
import retrofit2.http.Header;

/**
 * Created by akimaleo on 13.11.16.
 */

public class UserService implements UserApi {

    @Override
    public Call<String> check(@Header("Authorization") String token) {
        UserApi eApi = ServicesFactory.getRetrofit().create(UserApi.class);
        Call<String> call = eApi.check(token);
        return call;
    }

    public Call<Void> authorization(UserDto u) {
        UserApi eApi = ServicesFactory.getRetrofit().create(UserApi.class);
        Call<Void> call = eApi.authorization(u);
        return call;
    }


    public Call<Void> registration(UserDto u) {
        UserApi eApi = ServicesFactory.getRetrofit().create(UserApi.class);
        return eApi.registration(u);
    }


}
