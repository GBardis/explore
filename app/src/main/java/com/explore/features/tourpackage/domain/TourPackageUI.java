package com.explore.features.tourpackage.domain;

import lombok.Getter;
import lombok.Setter;

public class TourPackageUI {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Double avgrating;

    public TourPackageUI(String name, Double avgrating) {
        this.setName(name);
        this.setAvgrating(avgrating);
    }
}
