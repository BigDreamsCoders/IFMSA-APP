package com.pedrodeveloper14.ifmsaelsalvador.database.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {

    @PrimaryKey
    @NonNull
    private String id;

    private String name;
    private String email;
    private String collage;

    public User(@NonNull String id, String name, String email, String collage) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.collage = collage;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }
}
