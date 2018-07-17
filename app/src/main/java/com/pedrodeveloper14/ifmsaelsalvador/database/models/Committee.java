package com.pedrodeveloper14.ifmsaelsalvador.database.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Committee {

    @PrimaryKey
    @NonNull
    private String name;

    private String imageUrl;

    public Committee(@NonNull String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
