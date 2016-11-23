package com.letit0or1.androidworldhistorytracker.entity;

import java.sql.Timestamp;

/**
 * Created by akimaleo on 23.11.16.
 */

public class EventIn {
    private int eventId;

    private String eventName;

    private double latitude;

    private double longitude;

    private Timestamp createDate;

    private int userId;

    public EventIn(String content, double latitude, double longitude, int userId) {
        this.eventName = content;
        this.longitude = longitude;
        this.latitude = latitude;
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public int getUserId() {
        return userId;
    }
}
