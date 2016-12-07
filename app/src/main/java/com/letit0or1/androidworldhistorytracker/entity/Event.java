package com.letit0or1.androidworldhistorytracker.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Timestamp;

@DatabaseTable(tableName = "accounts")
public class Event {

    @DatabaseField
    private int eventId;

    @DatabaseField
    private String eventName;

    @DatabaseField
    private double latitude;

    @DatabaseField
    private double longitude;

    @DatabaseField
    private Timestamp createDate;

    @DatabaseField
    private int userId;

    public Event() {
    }

    public Event(String content, double latitude, double longitude, int userId) {
        this.eventName = content;
        this.longitude = longitude;
        this.latitude = latitude;
        this.userId = userId;
    }

    public Event(int eventId, String eventName, double latitude, double longitude, Timestamp createDate, int userId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createDate = createDate;
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

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
