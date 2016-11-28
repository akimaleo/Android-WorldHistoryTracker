package com.letit0or1.androidworldhistorytracker.entity;

/**
 * Created by akimaleo on 23.11.16.
 */

public class EventAdd {

    private String content;
    private double latitude;
    private double longitude;

    public EventAdd(String content, double longitude, double latitude) {
        this.content = content;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
