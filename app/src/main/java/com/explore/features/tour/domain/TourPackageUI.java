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
  @Getter
    @Setter
    private String description;

    public TourPackageUI(String name, String area, double rating, String descriptioin) {
        this.name = name;
        this.area = area;
        this.rating = rating;
        this.description = descriptioin;
    }
}
