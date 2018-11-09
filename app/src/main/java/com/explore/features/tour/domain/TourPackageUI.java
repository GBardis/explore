package com.explore.features.tour.domain;

import com.explore.features.tourpackage.domain.TourPackageRegion;

import lombok.Getter;
import lombok.Setter;

public class TourPackageUI {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String area;

    @Getter
    @Setter
    private double rating;


    @Getter
    @Setter
    private String regionColor;


    @Getter
    @Setter
    private String ratingColor;


    public TourPackageUI(String name, String area, double rating) {
        this.name = name;
        this.area = area;
        this.rating = rating;
        this.ratingColor = findRatingColor(rating);
        this.regionColor = findRegionColor(area);
    }

    private String findRatingColor(Double avgRating) {
        if (avgRating < 2) {
            // RED
            return "#FF0000";
        } else if (avgRating > 2 && avgRating <= 3) {
            // YELLOW
            return "#E3FF28";
        } else {
            // GREEN
            return "#13FF16";
        }
    }

    private String findRegionColor(String region) {
        return TourPackageRegion.getRegionColorValue(region);
    }
}
