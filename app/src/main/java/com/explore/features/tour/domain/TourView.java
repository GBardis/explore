package com.explore.features.tour.domain;

import com.explore.features.reviewnew.domain.ReviewDomain;

import java.util.ArrayList;

public interface TourView {

    void showTourPackage(ArrayList<TourUI> tourUIArrayList, TourPackageUI tourPackageUI);

    void showTourPackageReviewList(ArrayList<ReviewUI> reviewUIArrayList);
}
