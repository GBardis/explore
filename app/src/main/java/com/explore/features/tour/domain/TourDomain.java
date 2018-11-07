package com.explore.features.tour.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "tours")
public class TourDomain{
    @Getter
    @PrimaryKey
    @NonNull
    private final int id;
    @Getter
    @Setter
    String title;
    @Getter
    @Setter
    String description;
    @Getter
    @Setter
    int price;
    @Getter
    @Setter
    String duration;
    @Getter
    @Setter
    String bullets;
    @Getter
    @Setter
    String keywords;

    public TourDomain(@NonNull int id, String title, String description, int price, String duration, String bullets, String keywords) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.bullets = bullets;
        this.keywords = keywords;
    }
}
