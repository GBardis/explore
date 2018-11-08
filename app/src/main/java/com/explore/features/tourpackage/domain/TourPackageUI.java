package com.explore.features.tourpackage.domain;

import lombok.Getter;
import lombok.Setter;

public class TourPackageUI {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String region;
    @Getter
    @Setter
    private String ratingColor;
    @Getter
    @Setter
    private String regionColor;
    @Getter
    @Setter
    private Double avgRating;
    @Getter
    @Setter
    private String placeId;

    public TourPackageUI(String id, String name, String region, Double avgRating, String placeId) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.ratingColor = findRatingColor(avgRating);
        this.regionColor = findRegionColor(region);
        this.avgRating = avgRating;
        this.placeId = placeId;
    }

    public TourPackageUI(String id, String name, String region, Double avgRating) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.ratingColor = findRatingColor(avgRating);
        this.regionColor = findRegionColor(region);
        this.avgRating = avgRating;
    }

    private String findRatingColor(Double avgRating) {
        if (avgRating < 2) {
            // RED
            return "#FF0000";
        } else if (avgRating > 2 || avgRating <= 3) {
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
