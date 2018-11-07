package com.explore.features.tourpackage.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "tourPackages")
public class TourPackageDomain {
    @Getter
    @NonNull
    @PrimaryKey
    public final String id;
    @Getter
    @Setter
    public String name;
    @Getter
    @Setter
    public Double rating;
    @Getter
    @Setter
    public String region;
    @Getter
    @Setter
    public String placeId;
    @Ignore
    @Getter
    @Setter
    public Bitmap placePhoto;

    public TourPackageDomain(@NonNull String id, String name, Double rating, String region, String placeId) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.region = region;
        this.placeId = placeId;
    }
}
