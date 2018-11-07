package com.explore.features.tour.domain;


public interface TourPresenter {

    void getTourPackage(String tourPackageId);

    void getTourList(String tourPackageId);

    void getTourPackageReviews(String tourPackageId);

}
