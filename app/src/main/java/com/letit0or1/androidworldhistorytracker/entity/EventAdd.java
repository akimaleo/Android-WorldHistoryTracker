package com.letit0or1.androidworldhistorytracker.entity;

/**
 * Created by akimaleo on 23.11.16.
 */

public class EventAdd {
    String eventName;
    double longitude;
    double latitude;

    public EventAdd(String content, double longitude, double latitude) {
        this.eventName = content;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
