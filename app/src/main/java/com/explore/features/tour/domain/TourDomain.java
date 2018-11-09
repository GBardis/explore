package com.explore.features.tour.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.explore.features.tourpackage.domain.TourPackageDomain;

import lombok.Getter;
import lombok.Setter;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "tours")
public class TourDomain {

    @Getter
    @PrimaryKey
    @NonNull
    public final int id;
    @Getter
    @NonNull
    @ForeignKey(entity = TourPackageDomain.class,
            parentColumns = "id",
            childColumns = "tourPackageId",
            onDelete = CASCADE)
    public String tourPackageId;
    @Getter
    @Setter
    public String title;
    @Getter
    @Setter
    public String description;
    @Getter
    @Setter
    public int price;
    @Getter
    @Setter
    public String duration;
    @Getter
    @Setter
    public String bullets;
    @Getter
    @Setter
    public String keywords;

    public TourDomain(@NonNull int id, String title, String description, int price, String duration, String bullets, String keywords, String tourPackageId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.bullets = bullets;
        this.keywords = keywords;
        this.tourPackageId = tourPackageId;
    }
}
