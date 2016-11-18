package com.letit0or1.androidworldhistorytracker.entity;

import java.sql.Timestamp;

/**
 * Created by akimaleo on 18.11.16.
 */

public class EventSearchDto {
    double longitude;
    double latitude;
    int radius;

    Timestamp from;
    Timestamp to;

    public EventSearchDto(double longitude, double latitude, int radius, Timestamp from, Timestamp to) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
        this.from = from;
        this.to = to;
    }

    public EventSearchDto(double longitude, double latitude, Timestamp from, Timestamp to) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.from = from;
        this.to = to;
    }
}
