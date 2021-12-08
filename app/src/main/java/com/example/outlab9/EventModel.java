package com.example.outlab9;

import java.util.Date;

public class EventModel {
    private int id;
    private String mType;
    private String mDescription;
    private String mDate;

    EventModel(String type, String description, String date) {
        this.id = -1;
        this.mType = type;
        this.mDescription = description;
        this.mDate = date;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}