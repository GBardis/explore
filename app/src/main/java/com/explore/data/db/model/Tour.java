package com.explore.data.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Tour {
    @Getter
    @PrimaryKey
    public final String id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double rating;
    @Getter
    @Setter
    private String descTextSize;

    public Tour(String id, String name, double rating, String descTextSize) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.descTextSize = descTextSize;
    }
}
