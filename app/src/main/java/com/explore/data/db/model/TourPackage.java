package com.explore.data.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity
public class TourPackage {
    @Getter
    @PrimaryKey
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

    public TourPackage(String id, String name, Double rating, String ratingColor, String regionColor) {
        this.id = id;
        this.setName(name);
        this.setRating(rating);
        this.setRatingColor(ratingColor);
        this.setRegionColor(regionColor);
    }
}
