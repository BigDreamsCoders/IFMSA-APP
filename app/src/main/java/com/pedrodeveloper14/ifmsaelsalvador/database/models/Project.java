package com.pedrodeveloper14.ifmsaelsalvador.database.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Project {
    @PrimaryKey
    @NonNull
    private String id;

    private String
            hour, date, place, name, committee;
    private int took_part;

    public Project(@NonNull String id, String hour, String date, String place, String name, String committee, int took_part) {
        this.id = id;
        this.hour = hour;
        this.date = date;
        this.place = place;
        this.name = name;
        this.committee = committee;
        this.took_part = took_part;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public int getTook_part() {
        return took_part;
    }

    public void setTook_part(int took_part) {
        this.took_part = took_part;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommittee() {
        return committee;
    }

    public void setCommittee(String committee) {
        this.committee = committee;
    }
}
