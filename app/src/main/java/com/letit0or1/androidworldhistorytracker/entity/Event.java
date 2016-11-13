package com.letit0or1.androidworldhistorytracker.entity;

import android.graphics.PointF;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Timestamp;

@DatabaseTable(tableName = "accounts")
public class Event {

    @DatabaseField
    private int id;

    @DatabaseField
    private int user_id;

    @DatabaseField
    private String content;

    @DatabaseField
    private double latitude;

    @DatabaseField
    private double longitude;

    @DatabaseField
    private Timestamp dateTime;

    public Event() {
    }

    public Event(int id, int user_id, String content, double latitude, double longitude, Timestamp dateTime) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getContent() {
        return content;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
}
