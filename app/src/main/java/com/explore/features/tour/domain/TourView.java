package com.explore.features.tour.domain;

import com.explore.data.db.model.Tour;
import com.explore.data.db.model.TourPackage;

import java.util.ArrayList;

public interface TourView {

    void showTourPackage(ArrayList<TourDomain> tourDomainArrayList, TourPackageDomain tourPackageDomain);
}
