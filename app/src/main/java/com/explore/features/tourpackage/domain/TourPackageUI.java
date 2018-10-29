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
    private Double avgrating;

    public TourPackageUI(String id,String name, Double avgrating) {
        this.id = id;
        this.name = name;
        this.avgrating = avgrating;
    }
}
