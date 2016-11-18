package com.letit0or1.androidworldhistorytracker.webapp.service;

import com.letit0or1.androidworldhistorytracker.entity.Event;
import com.letit0or1.androidworldhistorytracker.entity.User;
import com.letit0or1.androidworldhistorytracker.entity.UserDto;
import com.letit0or1.androidworldhistorytracker.webapp.api.EventApi;
import com.letit0or1.androidworldhistorytracker.webapp.api.UserApi;
import com.letit0or1.androidworldhistorytracker.webapp.factory.ServicesFactory;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by akimaleo on 13.11.16.
 */

public class UserService {

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
