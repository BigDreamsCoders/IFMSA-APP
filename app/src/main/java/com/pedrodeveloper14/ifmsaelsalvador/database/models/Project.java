package com.pedrodeveloper14.ifmsaelsalvador.database.models;

public class Project {

    private String
    hour, date, place, name;
    private int took_part;

    public Project(String hour, String date, String place, String name) {
        this.hour = hour;
        this.date = date;
        this.place = place;
        this.name = name;
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
}
