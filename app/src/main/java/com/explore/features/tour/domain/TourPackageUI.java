package com.explore.features.tour.domain;

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


    public TourPackageUI(String name, String area, double rating) {
        this.name = name;
        this.area = area;
        this.rating = rating;
    }
}
