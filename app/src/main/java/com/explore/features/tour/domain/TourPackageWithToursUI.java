package com.explore.features.tour.domain;

import com.explore.data.db.model.Tour;
import com.explore.data.db.model.TourPackage;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class TourPackageWithToursUI {
    @Getter
    @Setter
    ArrayList<Tour> tourArrayList;
    TourPackage tourPackage;

    public TourPackageWithToursUI(ArrayList<Tour> tourArrayList, TourPackage tourPackage) {
        this.tourArrayList = tourArrayList;
        this.tourPackage = tourPackage;
    }
}
