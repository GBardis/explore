package com.explore.features.tourpackage.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
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
    public enum Region {
        CRETE("Crete"),
        PELOPONNESE("Peloponnese"),
        MACEDONIA("Mecodonia"),
        THESSALY("Thessaly"),
        THRACE("Threace"),
        AEAGEAN("Aegean"),
        IOANIAN("Ioannian"),
        STEREA_HELLAS("Sterea_hellas");


        private final String text;

        Region(final String text) {
            this.text = text;
        }

        @NonNull
        @Override
        public String toString() {
            return text;
        }
    }

    @Getter
    @Setter
    public String name;
    @Getter
    @Setter
    public Double rating;
    @Getter
    @Setter
    public String ratingColor;
    @Getter
    @Setter
    public String regionColor;

    public TourPackageDomain(@NonNull String id, String name, Double rating, String ratingColor, String regionColor) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.ratingColor = ratingColor;
        this.regionColor = regionColor;
    }
}
