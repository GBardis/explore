package com.explore.features.tour.domain;

import lombok.Getter;
import lombok.Setter;

public class TourUI {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter

    private double rating;

    public TourUI(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }
}
