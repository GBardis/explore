package com.explore.features.tourpackage.domain;

import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;

public class TourPackageDomain {
    @Getter
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
    private String name;
    @Getter
    @Setter
    private Double rating;
    @Getter
    @Setter
    private String ratingColor;
    @Getter
    @Setter
    private String regionColor;
    @Getter
    @Setter
    private String area;

    @Getter
    @Setter
    private String description;


    public TourPackageDomain(String id, String name, Double rating, String ratingColor, String regionColor) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.ratingColor = ratingColor;
        this.regionColor = regionColor;
    }

    public TourPackageDomain(String id, String name, Double rating, String ratingColor, String regionColor, String area, String description) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.ratingColor = ratingColor;
        this.regionColor = regionColor;
        this.area = area;
        this.description = description;
    }
}
