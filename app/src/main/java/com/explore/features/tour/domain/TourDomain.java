package com.explore.features.tour.domain;

import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

public class TourDomain{
    @Getter
    @PrimaryKey
    private final String id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double rating;
    @Getter
    @Setter
    private String descTextSize;
    @Getter
    @Setter
    private String description;

    public TourDomain(String id, String name, double rating, String description) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.description = description;
    }
}
