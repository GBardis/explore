package com.explore.features.tour.domain;

import lombok.Getter;
import lombok.Setter;

public class TourPackageDomain{

    @Getter
    @Setter
    private String id;

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
    private String area;

    public TourPackageDomain(String id, String name, double rating, String descTextSize, String area) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.descTextSize = descTextSize;
        this.area = area;
    }
}
