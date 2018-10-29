package com.explore.features.tour.domain;

import com.explore.features.reviewnew.domain.ReviewDomain;

import java.util.ArrayList;

public interface TourView {

    void showTourPackage(TourPackageUI tourPackageUI);

    void showTourList(ArrayList<TourUI> tourUIArrayList);

    void showTourPackageReviewList(ArrayList<ReviewUI> reviewUIArrayList);
}
