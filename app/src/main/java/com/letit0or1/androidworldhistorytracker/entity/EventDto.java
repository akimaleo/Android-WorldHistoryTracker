package com.letit0or1.androidworldhistorytracker.entity;

/**
 * Created by akimaleo on 18.11.16.
 */

public class EventDto {
    String content;
    double longitude;
    double latitude;

    public EventDto(String content, double longitude, double latitude) {
        this.content = content;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
