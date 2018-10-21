package com.explore.features.tourpackage.domain;

import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;

public class TourPackageDomain {
    @Getter
    @Setter
    public final String id;

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

    public TourPackageDomain(String id, String name, Double rating, String ratingColor, String regionColor) {
        this.id = id;
        this.setName(name);
        this.setRating(rating);
        this.setRatingColor(ratingColor);
        this.setRegionColor(regionColor);
    }
}
