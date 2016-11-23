package com.letit0or1.androidworldhistorytracker.entity;

import java.sql.Timestamp;

/**
 * Created by akimaleo on 18.11.16.
 */

public class EventSearch {
    double latitude;
    double longitude;
    int radius;

    Timestamp from;
    Timestamp to;

    public EventSearch(double latitude, double longitude, int radius, Timestamp from, Timestamp to) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.from = from;
        this.to = to;
    }

    //    public EventSearch(double longitude, double latitude, int radius, Timestamp from, Timestamp to) {
//        this.longitude = longitude;
//        this.latitude = latitude;
//        this.radius = radius;
//        this.from = from;
//        this.to = to;
//    }

    public EventSearch(double longitude, double latitude, Timestamp from, Timestamp to) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.from = from;
        this.to = to;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getRadius() {
        return radius;
    }

    public Timestamp getFrom() {
        return from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }
}
