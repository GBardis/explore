package com.explore.features.tourpackage.domain;

import com.explore.data.db.model.TourPackage;

import java.util.ArrayList;

public interface TourPackageView {

    void showTourPackages(ArrayList<TourPackage> tourPackageArrayList);

}
