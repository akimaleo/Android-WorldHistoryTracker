package com.letit0or1.androidworldhistorytracker.entity;

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
    private Timestamp createDate;


    public Event() {
    }

    public Event(int id, int user_id, String content, double latitude, double longitude, Timestamp createDate) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createDate = createDate;
    }

    public Event(String content, double latitude, double longitude, Timestamp createDate) {
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createDate = createDate;
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

    public Timestamp getCreateDate() {
        return createDate;
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

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
