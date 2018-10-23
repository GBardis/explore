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

    @Getter
    @Setter
    private String description;


    public TourPackageDomain(String id, String name, double rating, String area, String description) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.area = area;
        this.description = description;
    }
}
